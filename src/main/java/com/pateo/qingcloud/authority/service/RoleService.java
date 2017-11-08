package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Role;
import com.pateo.qingcloud.authority.domain.rbac.RoleResource;
import com.pateo.qingcloud.authority.exception.DBException;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import com.pateo.qingcloud.authority.repositry.ResourceRepository;
import com.pateo.qingcloud.authority.repositry.RoleRepository;
import com.pateo.qingcloud.authority.repositry.RoleResourceRepository;
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
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleResourceRepository roleResourceRepository;

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
    public RoleVo getInfo(Long id){
        Role role = roleRepository.findOne(id);
        RoleVo info = new RoleVo();
        BeanUtils.copyProperties(role, info);
        return info;
    }
    /**
     * 查询所有角色
     * @return
     */
    public List<RoleVo> findAll(){
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

}
