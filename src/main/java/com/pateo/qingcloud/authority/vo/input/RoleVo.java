/**
 * 
 */
package com.pateo.qingcloud.authority.vo.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author seangogo
 */
@ApiModel("角色模型")
@Setter
@Getter
public class RoleVo {

	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "项目id")
	private String projectId;

	@ApiModelProperty(value = "角色名称(必输)")
	private String name;

	@ApiModelProperty(value = "角色简介(必输)")
	private String remark;

	@ApiModelProperty(value = "角色编码(必输)")
	private String code;
}
