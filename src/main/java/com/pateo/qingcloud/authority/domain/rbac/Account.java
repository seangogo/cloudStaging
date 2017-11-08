package com.pateo.qingcloud.authority.domain.rbac;

import com.pateo.qingcloud.authority.domain.User;
import com.pateo.qingcloud.authority.menu.AccountStatus;
import com.pateo.qingcloud.authority.menu.AccountWay;
import com.pateo.qingcloud.authority.support.BaseEntity;
import com.pateo.qingcloud.authority.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_account")
public class Account extends BaseEntity<String> implements UserDetails {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;


    /**
     * 用户的所有角色
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<RoleAccount> roles = new HashSet<>();


    /**
     * 用户有权访问的url下的所有(projectId)，不持久化到数据库
     */
    @Transient
    private Set<String> urls = new HashSet<>();

    /**
     * 用户有权访问的所有projectId，不持久化到数据库
     */
    @Transient
    private Set<String> projectIds = new HashSet<>();


    /**
     * 用户有权的所有资源id，不持久化到数据库
     */
    @Transient
    private Set<String> resourceIds = new HashSet<>();

    /**
     *  账户状态
     *  0 - 未激活  1 - 正常 2 - 停用 3 - 锁定
     */
    @Getter
    @Setter
    private AccountStatus status;

    /**
     * 密码过期时间
     */
    @Getter
    @Setter
    private Date expire_time;

    /**
    *密码修改时间
    * */
    @Getter
    @Setter
    private Date updatePassword;

    /**
     * 账户锁定时间
     */
    @Getter
    @Setter
    private long account_lock_time;

    /**
     * 创建方式:0手动，1自动勾选，2自动导入
     */
    @Getter
    @Setter
    private AccountWay accountWay;

    /**
     * 关联用户
     */
    @ManyToOne()
    @Getter
    @Setter
    private User user;

    /**
     * olderPasswords
     * 用户修改过的密码集合
     */
    @ElementCollection
    @Getter
    @Setter
    private Set<String> oldPasswords;

    /**
     * 可操作的项目Id(随调用的方法不用而更新)
     */
    @Transient
    private Set<String> operableProjectIds =new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<RoleAccount> userRoles = this.getRoles();
        if(userRoles != null)
        {
            for (RoleAccount roleAccount : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+roleAccount.getRole().getCode()
                        +"_"+roleAccount.getRole().getProjectId());
                authorities.add(authority);
            }
        }
        Set<String> urls = this.getUrls();
        Set<String> projectIds = this.getProjectIds();
        if (urls!=null){
            for (String url:urls){
                for (String id:projectIds){
                    authorities.add(new SimpleGrantedAuthority(url+"_"+id));
                }
            }
        }
        return authorities;
    }

    /**
     * @return
     */
    public Set<String> getAllResourceIds() {
        init(resourceIds);
        forEachResource(resource -> resourceIds.add(resource.getId()));
        return resourceIds;
    }


    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.userName = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 账户是否也停用
     */
    @Override
    public boolean isAccountNonExpired() {
        return !this.getStatus().equals(AccountStatus.DISABLE);
    }

    /**
     *
     * @return 账户是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return !this.getStatus().equals(AccountStatus.LOCK);
    }

    /**
     * @return 密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return TimeUtils.UDateToLocalDate(this.getExpire_time()).isAfter(LocalDate.now());
    }

    /**
     * @return 账户是否删除
     */
    @Override
    public boolean isEnabled() {
        return this.getDelFlag().equals(0)&&this.getUser()!=null;
    }

    /**
     * @return the roles
     */
    public Set<RoleAccount> getRoles() {
        return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(Set<RoleAccount> roles) {
        this.roles = roles;
    }


    /**
     * @return the urls
     */
    public Set<String> getUrls() {
        init(urls);
        forEachResource(resource -> urls.addAll(resource.getUrls()));
        return urls;
    }


    /**
     * @param urls
     *            the urls to set
     */
    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }



    /**
     * @return the projectIds
     */
    public Set<String> getProjectIds() {
        init(projectIds);
        forEachRoles(role -> projectIds.add(role.getProjectId()));
        return projectIds;
    }


    /**
     * @param projectIds
     * the urls to set
     */
    public void setProjectIds(Set<Long> projectIds) {
        this.urls = urls;
    }

    /**
     * @param data
     */
    private void init(Set<?> data){
        if (CollectionUtils.isEmpty(data)) {
            if (data == null) {
                data = new HashSet<>();
            }
        }
    }

    /**
     * @param consumer
     */
    private void forEachResource(Consumer<Resource> consumer) {
        for (RoleAccount role : roles) {
            for (RoleResource resource : role.getRole().getResources()) {
                consumer.accept(resource.getResource());
            }
        }
    }

    /**
     * @param consumer
     */
    private void forEachRoles(Consumer<Role> consumer) {
        for (RoleAccount roleAccount : roles) {
            consumer.accept(roleAccount.getRole());
        }
    }

    public Set<String> getOperableProjectIds() {
        return operableProjectIds;
    }

    public void setOperableProjectIds(Set<String> operableProjectIds) {
        this.operableProjectIds = operableProjectIds;
    }
}
