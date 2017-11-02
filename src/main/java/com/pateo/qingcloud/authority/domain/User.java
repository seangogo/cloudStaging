package com.pateo.qingcloud.authority.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pateo.qingcloud.authority.menu.Sex;
import com.pateo.qingcloud.authority.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author sean
 * @date 2017/11/2
 */
@Entity
@Table(name = "opt_user")
@Getter
@Setter
public class User extends BaseEntity<String>{

    /**
     * 用户姓名
     */
    private String userName;


    /**
     * 性别
     */
    private Sex sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    @Email
    private String email;


    /**
     *  头像url
     */
    private String imgUrl;


    /**
     * 品牌ID
     */
    private String brandId;

    /**
     * 组织Id
     */
    private String organizeId;



    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "opt_user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("users")
    private Set<Role> roles = new HashSet();

}