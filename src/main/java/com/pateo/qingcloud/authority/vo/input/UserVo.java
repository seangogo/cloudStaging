package com.pateo.qingcloud.authority.vo.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;

@ApiModel(value = "管理平台用户VO")
public class UserVo {
	
	@ApiModelProperty(value = "ID")
	private String sid;

	
	@ApiModelProperty(value = "用户名(必输)")
	private String userName;

	@ApiModelProperty(value = "性别(必输)")
	private String sex;
	
	@ApiModelProperty(value = "手机号")
	private String phone;
	
	@ApiModelProperty(value = "电子邮箱")
	@Email
	private String email;
	
	@ApiModelProperty(value = "项目ID(必输)")
	private String projectId;
	
	@ApiModelProperty(value = "项目名称")
	private String projectName;
	
	@ApiModelProperty(value = "品牌ID(必输)")
	private String brandId;
	
	@ApiModelProperty(value = "品牌名称")
	private String brandName;
	
	@ApiModelProperty(value = "组织名称")
	private String organizeId;
	
	@ApiModelProperty(value = "用户状态：1-禁用；0-启用(必输)")
	private String userStatus;
	
	@ApiModelProperty(value = "头像url")
	private String imgUrl;
}
