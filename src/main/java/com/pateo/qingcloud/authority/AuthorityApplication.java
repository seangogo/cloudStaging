package com.pateo.qingcloud.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author seangogo
 */
@SpringBootApplication
@EnableJpaAuditing
public class AuthorityApplication {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
		SpringApplication.run(AuthorityApplication.class, args);
	}
}
