package com.pateo.qingcloud.authority.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;

/**
 * @author jh
 */
@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
    @Value("${app.version}")
    private String version;
    @Override
    public String getCurrentAuditor() {
        log.info("版本：{}",version);
        return "10086";
        // Can use Spring Security to return currently logged in user
        // return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
    }
}