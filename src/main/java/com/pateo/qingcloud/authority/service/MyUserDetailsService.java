package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.Account;
import com.pateo.qingcloud.authority.menu.AccountStatus;
import com.pateo.qingcloud.authority.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by sean on 2017/11/2.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Account account=accountService.findUserByLoginName(loginName);
        //账户是否删除
        boolean isNotDel=account.getDelFlag().equals(0);
        //账户是否也停用
        boolean isNotStop=!account.getStatus().equals(AccountStatus.DISABLE);
        //密码是否过期
        boolean isNotExpire=TimeUtils.UDateToLocalDate(account.getExpire_time()).isAfter(LocalDate.now());
        //账户是否锁定
        boolean isNotLock=!account.getStatus().equals(AccountStatus.LOCK);
      //  List<Resource> resources=new ArrayList<>();
      // account.getUser().getRoles().stream().forEach(role -> resources.addAll(role.getFunction()));
        return new org.springframework.security.core.userdetails.User(loginName,account.getPassword(),
                isNotDel,isNotStop,isNotExpire,isNotLock,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
