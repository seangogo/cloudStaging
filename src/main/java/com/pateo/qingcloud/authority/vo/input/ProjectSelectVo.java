package com.pateo.qingcloud.authority.vo.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sean on 2017/11/14.
 */
@ApiModel( "项目查询VO")
@Setter
@Getter
@ToString
public class ProjectSelectVo {
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目code")
    private String projectCode;

    @ApiModelProperty(value = "项目key")
    private String projectKey;

    @ApiModelProperty(value = "描述")
    private String descr;
}
