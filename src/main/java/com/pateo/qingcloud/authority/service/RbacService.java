/**
 * 
 */
package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author zhailiang
 *
 */
@Component("rbacService")
@Slf4j
public class RbacService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();

		boolean hasPermission = false;

		if (principal instanceof Account) {
			//如果用户名是admin，就永远返回true
			if (StringUtils.equals(((Account) principal).getUsername(), "admin1")) {
				hasPermission = true;
			} else {
				// 读取用户所拥有权限的所有URL
				Account account=(Account) principal;
				Set<String> urls = account.getUrls();
				Set<Long> projectId=account.getProjectIds();
				log.info("项目ID:",projectId);
				for (String url : urls) {
					if (antPathMatcher.match(url, request.getRequestURI())) {
						hasPermission = true;
						break;
					}
				}
			}
		}

		return hasPermission;
	}

}
