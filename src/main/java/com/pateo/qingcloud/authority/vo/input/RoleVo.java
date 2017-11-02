package com.pateo.qingcloud.authority.vo.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "管理平台角色VO")
public class RoleVo {
	
	@ApiModelProperty(value = "ID")
	private String sid;
	
	@ApiModelProperty(value = "项目ID")
	private String projectId;
	
	@ApiModelProperty(value = "角色名称(必输)")
	private String roleName;
	
	@ApiModelProperty(value = "角色描述")
	private String descriptrion;
	
	@ApiModelProperty(value = "角色类型：1-功能角色；2-数据角色(必输)")
	private String roleType;
	
	@ApiModelProperty(value = "角色状态：1-禁用；0-启用(必输)")
	private String roleStatus;


	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescriptrion() {
		return descriptrion;
	}

	public void setDescriptrion(String descriptrion) {
		this.descriptrion = descriptrion;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public RoleVo() {
		super();
	}
	
}
