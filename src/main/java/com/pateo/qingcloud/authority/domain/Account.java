package com.pateo.qingcloud.authority.domain;

import com.pateo.qingcloud.authority.menu.AccountStatus;
import com.pateo.qingcloud.authority.menu.AccountWay;
import com.pateo.qingcloud.authority.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_account")
@Getter
@Setter
public class Account extends BaseEntity<String> {
    /**
     * 用户名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     *  账户状态
     *  0 - 未激活  1 - 正常 2 - 停用 3 - 锁定
     */
    private AccountStatus status;

    /**
     * 密码过期时间
     */
    private Date expire_time;

    /**
    *密码修改时间
    * */
    private Date updatePassword;

    /**
     * 账户锁定时间
     */
    private long account_lock_time;

    /**
     * 创建方式:0手动，1自动勾选，2自动导入
     */
    private AccountWay accountWay;

    /**
     * 关联用户
     */
    @ManyToOne()
    private User user;
}
