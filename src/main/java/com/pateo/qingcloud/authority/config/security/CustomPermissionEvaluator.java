package com.pateo.qingcloud.authority.config.security;

/**
 * Created by sean on 2017/11/8.
 */

import com.pateo.qingcloud.authority.config.security.properties.SecurityProperties;
import com.pateo.qingcloud.authority.domain.rbac.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Set;

/**
 * @author seangogo
 */
@Configuration
@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SecurityProperties securityProperties;
    //普通的targetDomainObject判断
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        log.error("进入方法级别验证");
        Object principal = authentication.getPrincipal();
        if (principal instanceof Account) {
            Account account=(Account) principal;
            //如果用户名是，就永远返回true
            if (StringUtils.equals((account).getUsername(), securityProperties.getBrowser().getSysdba()+"1")) {
                return true;
            } else {
                //获取所有项目权限
                Set<String> projectIds=account.getProjectIds();
                account.getOperableProjectIds().clear();
                //遍历项目对应角色所拥有的资源
                for (String id:projectIds){
                    String urlId=permission.toString()+id;
                    log.info("urlId:{}",urlId);
                    if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(urlId))){
                        account.getOperableProjectIds().add(id);
                    }
                }
                if (account.getOperableProjectIds().size()>0){
                    return true;
                }
            }
        }
        return false;
    }
    //用于ACL的访问控制
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
