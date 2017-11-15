package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.domain.rbac.Resource;
import com.pateo.qingcloud.authority.domain.rbac.Role;
import com.pateo.qingcloud.authority.exception.DBException;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import com.pateo.qingcloud.authority.repositry.AccountRepository;
import com.pateo.qingcloud.authority.repositry.ResourceRepository;
import com.pateo.qingcloud.authority.repositry.RoleRepository;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import com.pateo.qingcloud.authority.vo.input.ResourceVo;
import com.pateo.qingcloud.authority.vo.rbac.ResourceInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sean
 * @date 2017/11/6
 */
@Service
@Transactional
public class ResourceService extends BaseServiceImpl<Resource,String> {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;



    @Override
    public BaseRepository<Resource, String> getBaseDao() {
        return this.resourceRepository;
    }

    /**
     * 获取资源树
     *
     * @param accountId 账户ID
     * @date
     * @since 1.0.0
     */
    public ResourceInfo getTree(String accountId){
        Account account = accountRepository.findOne(accountId);
        return resourceRepository.findByName("根节点").toTree(account);
    }

    /**
     * 根据资源ID获取资源信息
     *
     * @param id 资源ID
     * @return ResourceInfo 资源信息
     * @date  2015年7月10日下午7:01:48
     * @since 1.0.0
     */

    public ResourceVo getInfo(String id)throws DBException{
        if (!resourceRepository.exists(id)){
            throw new DBException(ResultEnum.OBJ_NOT_EXIST);
        }
        Resource resource = resourceRepository.findOne(id);
        ResourceVo resourceVo = new ResourceVo();
        BeanUtils.copyProperties(resource, resourceVo);
        return resourceVo;
    }

    /**
     * 新增资源
     *
     * @param vo 页面传入的资源信息
     * @return ResourceInfo 资源信息
     * @date  2017年11月07日下午7:01:51
     * @since 1.0.0
     */
    public void save(ResourceVo vo)throws DBException {
        Resource resource=new Resource();
        Resource parent = resourceRepository.findOne(vo.getParent());
        if(parent == null){
            throw new DBException(ResultEnum.PARENT_NOT_EXIST);
        }
        //修改
        if (!StringUtils.isEmpty(vo.getId())){
            resource=resourceRepository.findOne(vo.getId());
            if (resource==null||StringUtils.isEmpty(resource.getId())){
                throw new DBException(ResultEnum.RESOURCE_NOT_EXIST);
            }
        }
        BeanUtils.copyProperties(vo, resource);
        parent.addChild(resource);
        Date date=new Date();
        resource.setSort(date.getTime());//1700年到现在已经过去的毫秒数
        resourceRepository.save(resource);
    }
    /**
     * 更新资源
     *
     * @param info 页面传入的资源信息
     * @return ResourceInfo 资源信息
     * @date  2015年7月10日下午7:01:54
     * @since 1.0.0
     */
    public ResourceInfo update(ResourceInfo info){
        Resource resource = resourceRepository.findOne(info.getId());
        BeanUtils.copyProperties(info, resource);
        return info;
    }


    /**
     * 根据指定ID删除资源信息
     *
     * @param id 资源ID
     * @date  2015年7月10日下午7:01:57
     * @since 1.0.0
     */
    public void delete(String id){
        resourceRepository.delete(id);
    }
    /**
     * 上移/下移资源
     * @param id
     * @param up
     */
    public String move(String id, boolean up){
        Resource resource = resourceRepository.findOne(id);
        long index = resource.getSort();
        List<Resource> childs = resource.getParent().getChilds();
        for (int i = 0; i < childs.size(); i++) {
            Resource current = childs.get(i);
            if(current.getId().equals(id)) {
                if(up){
                    if(i != 0) {
                        Resource pre = childs.get(i - 1);
                        resource.setSort(pre.getSort());
                        pre.setSort(index);
                        resourceRepository.save(pre);
                    }
                }else{
                    if(i != childs.size()-1) {
                        Resource next = childs.get(i + 1);
                        resource.setSort(next.getSort());
                        next.setSort(index);
                        resourceRepository.save(next);
                    }
                }
            }
        }
        resourceRepository.save(resource);
        return resource.getParent().getId();
    }

    /**
     * 获取角色资树
     * @param id
     * @param operableProjectIds
     * @return
     */
    public ResourceInfo getRoleTree(String id, Set<String> operableProjectIds) {
        Role role  = roleRepository.findOne(id);
        if (!operableProjectIds.contains("0")
                &&!operableProjectIds.contains(role.getProjectId())){
            throw  new DBException(ResultEnum.PROJECTIDID_NOT_EXIST);

        }
        return resourceRepository.findByName("根节点").roletoTree(role);
    }
}
