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
 * 角色资源关系表
 * 
 * @author sean
 *
 */
@Entity
@Table(name = "opt_role_resource")
@Getter
@Setter
public class RoleResource extends BaseEntity<String>{
	/**
	 * 角色
	 */
	@ManyToOne
	private Role role;
	/**
	 * 资源
	 */
	@ManyToOne
	private Resource resource;
}
