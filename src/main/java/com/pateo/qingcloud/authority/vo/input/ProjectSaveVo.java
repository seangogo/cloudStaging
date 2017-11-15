package com.pateo.qingcloud.authority.vo.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author seangogo
 */
@Setter
@Getter
public class ProjectSaveVo {

	@ApiModelProperty(value = "项目id")
	private String id;

	@NotNull
	@ApiModelProperty(value = "项目名称")
	private String projectName;

	@NotNull
	@ApiModelProperty(value = "项目code")
	private String projectCode;

	@NotNull
	@ApiModelProperty(value = "项目key")
	private String projectKey;


	@ApiModelProperty(value = "项目描述")
	private String descr;

	
}
