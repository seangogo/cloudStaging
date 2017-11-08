package com.pateo.qingcloud.authority.domain.rbac;

import com.pateo.qingcloud.authority.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate
public class Role extends BaseEntity<String>{
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
    private String projectId;


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
