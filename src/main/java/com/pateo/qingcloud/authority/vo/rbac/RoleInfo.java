/**
 * 
 */
package com.pateo.qingcloud.authority.vo.rbac;

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
public class RoleInfo {
	@ApiModelProperty("id")
	private String id;

	@ApiModelProperty("角色名称")
	private String name;

	@ApiModelProperty("角色简介")
	private String remark;

	@ApiModelProperty("角色编码")
	private String code;

	@ApiModelProperty("项目主键")
	private Long   projectId;
}
