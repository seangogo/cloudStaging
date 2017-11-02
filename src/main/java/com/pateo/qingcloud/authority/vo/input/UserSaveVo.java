package com.pateo.qingcloud.authority.vo.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "用户添加输入VO")
public class UserSaveVo {

	@ApiModelProperty(value = "用户Vo")
	private UserVo userVo;
	
	@ApiModelProperty(value = "角色列表")
	private List<RoleVo> roleList;

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public List<RoleVo> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleVo> roleList) {
		this.roleList = roleList;
	}

	public UserSaveVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
