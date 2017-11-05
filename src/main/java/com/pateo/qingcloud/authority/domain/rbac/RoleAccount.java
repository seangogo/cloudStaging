/**
 * 
 */
package com.pateo.qingcloud.authority.domain.rbac;

import com.pateo.qingcloud.authority.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 角色用户关系表
 * 
 * @author sean
 *
 */
@Entity
@Table(name = "opt_role_account")
@Getter
@Setter
public class RoleAccount extends BaseEntity<String> {

	/**
	 * 角色
	 */
	@ManyToOne
	private Role role;
	/**
	 * 账户
	 */
	@ManyToOne
	private Account account;

}
