package com.pateo.qingcloud.authority.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pateo.qingcloud.authority.menu.ResourceType;
import com.pateo.qingcloud.authority.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_resource")
@Getter
@Setter
public class Resource extends BaseEntity<String> {

    /**
     * 资源名称
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 资源编码
     */
    @Column(name = "code", length = 50)
    private String code;

    /**
     * 0=目录 1=功能 2=按钮
     */
    private ResourceType resourceType;

    /**
     * 资源访问URL
     */
    @Column(name = "url", length = 200)
    private String url;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 上级资源层级编码
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 层级编码
     */
    @Column(name = "levelCode", length = 36)
    private Integer levelCode;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 简介
     */
    @Column(name = "remark", length = 1000)
    private String remark;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "opt_role_resource", joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("resources")
    private Set<Role> roles = new HashSet();

}
