/**
 * 
 */
package com.pateo.qingcloud.authority.config.security;

import com.pateo.qingcloud.authority.config.security.properties.SecurityProperties;
import com.pateo.qingcloud.authority.support.AuditorAwareImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author seangogo
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityCoreConfig {
    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditorAwareImpl();
    }
}
