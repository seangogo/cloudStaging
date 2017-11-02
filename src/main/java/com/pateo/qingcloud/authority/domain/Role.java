package com.pateo.qingcloud.authority.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Role extends BaseEntity<String>{
    /**
     * 角色名称
     */
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
     * 排序
     */
    private Long   sort;
    /**
     * 项目主键
     */
    private Long   projectId;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "opt_user_role", joinColumns = @JoinColumn(name = "role_id",
            referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id",
            referencedColumnName = "id"))
    @JsonIgnoreProperties("roles")
    private Set<User> users = new HashSet();


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @OrderBy(value="sort asc")
    @JoinTable(name = "opt_role_resource",
            joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "resource_id", referencedColumnName = "id") })
    @JsonIgnoreProperties("roles")
    private Set<Resource> function= new HashSet();
    /*
    *
	/**
	 * 角色类型
	 * 1-功能角色；2-数据角色
    @Column(name = "role_type")
    private String roleType;
    /**
     * 角色状态
    @Column(name = "role_status")
    private String roleStatus;
    */

}
