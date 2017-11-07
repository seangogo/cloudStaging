package com.pateo.qingcloud.authority.domain.rbac;

import com.pateo.qingcloud.authority.menu.ResourceType;
import com.pateo.qingcloud.authority.support.BaseEntity;
import com.pateo.qingcloud.authority.vo.rbac.ResourceInfo;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_resource")
@Getter
@Setter
@DynamicUpdate
public class Resource extends BaseEntity<String> {

    /**
     * 资源名称 如xx菜单，xx按钮
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 资源编码
     */
    @Column(name = "code", length = 50)
    private String code;

    /**
     * 资源类型
     */
    private ResourceType type;

    /**
     * 实际需要控制权限的url
     */
    @ElementCollection
    private Set<String> urls;

    /**
     * 资源链接
     */
    private String link;
    /**


    /**
     * 父资源
     */
    @ManyToOne
    private Resource parent;

    /**
     * 序号
     */
    private Long sort=System.currentTimeMillis();

    /**
     * 子资源
     */

    @OneToMany(mappedBy = "parent")
    @OrderBy("sort ASC")
    private List<Resource> childs = new ArrayList<>();

    public ResourceInfo toTree(Account account) {
        ResourceInfo result = new ResourceInfo();
        BeanUtils.copyProperties(this, result);
        Set<String> resourceIds = account.getAllResourceIds();

        List<ResourceInfo> children = new ArrayList();
        for (Resource child : getChilds()) {
            if(StringUtils.equals(account.getUsername(), "admin") ||
                    resourceIds.contains(child.getId())){
                children.add(child.toTree(account));
            }
        }
        result.setChildren(children);
        return result;
    }

    /**
     * 图标
     */
    private String icon;

    /**
     * 简介
     */
    @Column(name = "remark", length = 1000)
    private String remark;

    public void addChild(Resource child) {
        childs.add(child);
        child.setParent(this);
    }
}
