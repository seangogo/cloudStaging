package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.domain.rbac.Resource;
import com.pateo.qingcloud.authority.repositry.AccountRepository;
import com.pateo.qingcloud.authority.repositry.ResourceRepository;
import com.pateo.qingcloud.authority.vo.rbac.ResourceInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author sean
 * @date 2017/11/6
 */
@Service
@Transactional
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * 获取资源树
     *
     * @param accountId 账户ID
     * @date
     * @since 1.0.0
     */
    public ResourceInfo getTree(Long accountId){
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
    public ResourceInfo getInfo(Long id){
        Resource resource = resourceRepository.findOne(id);
        ResourceInfo resourceInfo = new ResourceInfo();
        BeanUtils.copyProperties(resource, resourceInfo);
        return resourceInfo;
    }

    /**
     * 新增资源
     *
     * @param info 页面传入的资源信息
     * @return ResourceInfo 资源信息
     * @date  2015年7月10日下午7:01:51
     * @since 1.0.0
     */
    public ResourceInfo create(ResourceInfo info){
        Resource parent = resourceRepository.findOne(info.getParentId());
        if(parent == null){
            parent = resourceRepository.findByName("根节点");
        }
        Resource resource = new Resource();
        BeanUtils.copyProperties(info, resource);
        parent.addChild(resource);
        info.setId(resourceRepository.save(resource).getId());
        return info;
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
    public void delete(Long id){
        resourceRepository.delete(id);
    }
    /**
     * 上移/下移资源
     * @param id
     * @param up
     */
    public Long move(Long id, boolean up){
        Resource resource = resourceRepository.findOne(id);
        int index = resource.getSort();
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
}
