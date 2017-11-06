package com.pateo.qingcloud.authority.support;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @author seangogo
 */
public class AuditorAwareImpl implements AuditorAware<Long> {
    @Value("${app.version}")
    private String version;
    @Override
    public Long getCurrentAuditor() {
        return ((Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}