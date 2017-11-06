package com.pateo.qingcloud.authority.domain.rbac;

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
@Table(name = "opt_role")
@Getter
@Setter
public class Role extends BaseEntity<Long>{
    /**
     * 角色名称
     */
    @Column(length = 20, nullable = false)
    private String name;
    /**
     * 角色简介
     */
    private String remark;
    /**
     * 角色编码
     */
    private String code;

    /**
     * 项目主键
     */
    private Long   projectId;


    /**
     * 角色拥有权限的资源集合
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<RoleResource> resources  = new HashSet<>();

    /**
     * 角色的用户集合
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<RoleAccount> accounts = new HashSet<>();
}
