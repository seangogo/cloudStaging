package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.repositry.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.provisioning.UserDetailsManager;
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
public class MyUserDetailsService implements UserDetailsManager {
    @Autowired
    private AccountService accountService;

    private UserCache userCache = new NullUserCache();

    @Autowired
    private AccountRepository accountRepository;

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

    @Override
    public void createUser(UserDetails userDetails) {
        log.info("createUser:{}",userDetails.toString());
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        log.info("UserDetails:{}",userDetails.toString());
    }

    @Override
    public void deleteUser(String s) {
        log.info("deleteUser:{}",s.toString());
    }

    @Override
    public void changePassword(String accountId, String newPassword) {
        int result=accountRepository.updatePassword(accountId,newPassword);
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Account account= (Account) authentication.getPrincipal();
        if(result>0) {
            account.setPassword(newPassword);
            userCache.removeUserFromCache(account.getUsername());
            SecurityContextHolder.clearContext();
        }
        log.info("changePassword:{}",accountId.toString());
    }

    @Override
    public boolean userExists(String s) {
        log.info("userExists:{}",s.toString());
        return false;
    }
}
