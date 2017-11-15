package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.domain.rbac.Role;
import com.pateo.qingcloud.authority.domain.rbac.RoleAccount;
import com.pateo.qingcloud.authority.domain.rbac.RoleResource;
import com.pateo.qingcloud.authority.exception.DBException;
import com.pateo.qingcloud.authority.menu.AccountStatus;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import com.pateo.qingcloud.authority.repositry.*;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import com.pateo.qingcloud.authority.utils.QueryResultConverter;
import com.pateo.qingcloud.authority.vo.input.RoleVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sean
 * @date 2017/11/6
 */
@Service
@Transactional
public class RoleService extends BaseServiceImpl<Role,String> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleAccountRepository roleAccountRepository;

    @Override
    public BaseRepository<Role, String> getBaseDao() {
        return this.roleRepository;
    }

    /**
     * 创建角色/修改角色
     * @param roleVo
     * @return
     */
    public void save(RoleVo roleVo){
        Role role = new Role();
        //修改
        if (StringUtils.isNotBlank(roleVo.getId())){
            role=roleRepository.findOne(roleVo.getId());
            if (StringUtils.isEmpty(role.getId())){
                throw new DBException(ResultEnum.ROLE_NOT_EXIST);
            }
            BeanUtils.copyProperties(roleVo, role,"projectId");
        }else {
            BeanUtils.copyProperties(roleVo, role);
        }

        roleRepository.save(role);
    }



    /**
     * 删除角色
     * @param id
     */
    public void delete(Long id){
        Role role = roleRepository.findOne(id);
        if(CollectionUtils.isNotEmpty(role.getAccounts())){
            throw new RuntimeException("不能删除有下挂用户的角色");
        }
        roleRepository.delete(id);
    }
    /**
     * 获取角色详细信息
     * @param id
     * @return
     */
    public RoleVo getInfo(String id) throws Exception{

        Role role = roleRepository.findOne(id);
        if (role==null){
            throw  new DBException(ResultEnum.ROLE_NOT_EXIST);
        }
        RoleVo info = new RoleVo();
        BeanUtils.copyProperties(role, info);
        return info;
    }



    /**
     * 查询所有角色
     * @return
     */
    public List<RoleVo> findAllVo(){
        return QueryResultConverter.convert(roleRepository.findAll(), RoleVo.class);
    }
    /**
     * 通过roleId 查询资源id
     * @param roleId
     * @return
     */
    public String[] getRoleResources(Long roleId){
        Role role = roleRepository.findOne(roleId);
        Set<String> resourceIds = new HashSet<>();
        for (RoleResource resource : role.getResources()) {
            resourceIds.add(resource.getResource().getId().toString());
        }
        return resourceIds.toArray(new String[resourceIds.size()]);
    }
    /**
     * @param roleId
     * @param resourceIds
     */
    public void setRoleResources(Long roleId, String resourceIds){
        resourceIds = StringUtils.removeEnd(resourceIds, ",");
        Role role = roleRepository.findOne(roleId);
        roleResourceRepository.delete(role.getResources());
        String[] resourceIdArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(resourceIds, ",");
        for (String resourceId : resourceIdArray) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRole(role);
            roleResource.setResource(resourceRepository.getOne(new Long(resourceId)));
            roleResourceRepository.save(roleResource);
        }
    }

    /**
     * 删除角色
     * @param id
     * @param projectIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(String id, Set<String> projectIds) throws Exception{
        Role role=roleRepository.findOne(id);
        if (role==null){
            throw new DBException(ResultEnum.DATA_NOT_EXIST);
        }
        if (role.getAccounts().size()>0||
                role.getResources().size()>0){
            throw new DBException(ResultEnum.DATA_BIND_NOT_DELETE);
        }
        if (!projectIds.contains("0")&&!projectIds.contains(role.getProjectId())){
            throw new DBException(ResultEnum.PROJECTIDID_NOT_EXIST);
        }
        delete(role.getId());
    }

    /**
     * 账户绑定角色
     * @param accountId
     * @param roleId
     * @param operableProjectIds
     */
    public void bindAccount(String accountId, String roleId, Set<String> operableProjectIds) {
        //验证权限
        Role role=roleRepository.findOne(roleId);
        Account account=accountRepository.findOne(accountId);
        if (account.getStatus()!= AccountStatus.NORMAL
                ||account.getDelFlag()==1){
            throw new DBException(ResultEnum.RESOURCE_NOT_EXIST);
        }
        if (role==null||account==null){
            throw new DBException(ResultEnum.DATA_NOT_EXIST);
        }
        if(!operableProjectIds.contains("0")&&
                !operableProjectIds.contains(role.getProjectId())){
            throw new DBException(ResultEnum.PROJECTIDID_NOT_EXIST);
        }
        RoleAccount roleAccount=new RoleAccount();
        roleAccount.setAccount(account);
        roleAccount.setRole(role);
        roleAccountRepository.save(roleAccount);
    }
}
