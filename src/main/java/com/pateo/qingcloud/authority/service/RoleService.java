package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Role;
import com.pateo.qingcloud.authority.domain.rbac.RoleResource;
import com.pateo.qingcloud.authority.repositry.ResourceRepository;
import com.pateo.qingcloud.authority.repositry.RoleRepository;
import com.pateo.qingcloud.authority.repositry.RoleResourceRepository;
import com.pateo.qingcloud.authority.utils.QueryResultConverter;
import com.pateo.qingcloud.authority.vo.rbac.RoleInfo;
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
     * 创建角色
     * @param roleInfo
     * @return
     */
    public RoleInfo create(RoleInfo roleInfo){
        Role role = new Role();
        BeanUtils.copyProperties(roleInfo, role);
        roleInfo.setId(roleRepository.save(role).getId());
        return roleInfo;
    }
    /**
     * 修改角色
     * @param roleInfo
     * @return
     */
    public RoleInfo update(RoleInfo roleInfo){
        Role role = roleRepository.findOne(roleInfo.getId());
        BeanUtils.copyProperties(roleInfo, role);
        return roleInfo;
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
    public RoleInfo getInfo(Long id){
        Role role = roleRepository.findOne(id);
        RoleInfo info = new RoleInfo();
        BeanUtils.copyProperties(role, info);
        return info;
    }
    /**
     * 查询所有角色
     * @return
     */
    public List<RoleInfo> findAll(){
        return QueryResultConverter.convert(roleRepository.findAll(), RoleInfo.class);
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
