package com.pateo.qingcloud.authority.vo.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sean on 2017/11/9.
 */
@ApiModel( "角色查询VO")
@Setter
@Getter
@ToString
public class RoleSelectVo {
    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("项目ID,只有超级管理员才有")
    private String projectId;

}
