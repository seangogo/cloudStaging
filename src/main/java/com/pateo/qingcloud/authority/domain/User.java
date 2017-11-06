package com.pateo.qingcloud.authority.domain;

import com.pateo.qingcloud.authority.menu.Sex;
import com.pateo.qingcloud.authority.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    private String realName;


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

    /**
     * 项目ID
     */
    private Long projectId;




}