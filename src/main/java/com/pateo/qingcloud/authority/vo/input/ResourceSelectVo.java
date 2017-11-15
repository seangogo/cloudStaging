package com.pateo.qingcloud.authority.vo.input;

import com.pateo.qingcloud.authority.menu.ResourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sean on 2017/11/15.
 */
@ApiModel( "菜单查询VO")
@Setter
@Getter
@ToString
public class ResourceSelectVo {
    @ApiModelProperty(value = "资源编号")
    private String code;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "父级菜单Id")
    private String parent;

    @ApiModelProperty(value = "资源类型")
    private ResourceType type;

}
