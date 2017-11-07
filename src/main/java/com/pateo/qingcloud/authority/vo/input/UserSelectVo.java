package com.pateo.qingcloud.authority.vo.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author seangogo
 */
@ApiModel( "用户查询VO")
@Setter
@Getter
@ToString
public class UserSelectVo {

    @ApiModelProperty(" 用户姓名")
    private String realName;

    @ApiModelProperty("性别 0=男 1=女 >1=all")
    private int sex;

    @ApiModelProperty("手机号：暂时没有加验证")
    private String phone;

    @ApiModelProperty("邮箱：暂时没有加验证")
    private String email;

    @ApiModelProperty("身份证号码：暂时没有加验证")
    private String idCard;

}