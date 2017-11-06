package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 *
 * @author sean
 * @date 2017/11/2
 */
@Component
@Slf4j
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;


    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        log.info("表单登录用户名:" + loginName);
        Account account=accountService.findUserByLoginName(loginName);

        Set<String> urls=account.getUrls();
        log.info("userinfo:{}",account.getUser().toString());
        for (String s:urls){
            log.info("url:{}",s);
        }
        return account;
    }
}
