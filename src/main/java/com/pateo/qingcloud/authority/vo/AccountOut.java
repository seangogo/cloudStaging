package com.pateo.qingcloud.authority.vo;

import com.pateo.qingcloud.authority.menu.Sex;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import java.util.Set;

/**
 * Created by sean on 2017/11/6.
 */
@Setter
@Getter
public class AccountOut {
    /**
     * 账户名
     */
    private String userName;
    /**
     * 真实姓名
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
     * 项目ID
     */
    private Set<String> projectIds;

}
