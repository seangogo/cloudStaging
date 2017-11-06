package com.pateo.qingcloud.authority.vo.input;


import com.pateo.qingcloud.authority.menu.AccountWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * @author seangogo
 */
@ApiModel("管理平台账户VO")
@Getter
@Setter
@ToString
public class AccountVo {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名:6-16字符",position = 1)
    @Pattern(regexp="^[\\w]{6,16}$",message = "账号输入有误")
    private String userName;

    /**
     * 密码
     */
    @Pattern(regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$",message = "密码输入有误")
    @ApiModelProperty(value = "未加密过的密码：字符+数字6-16",position = 2)
    private String password;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "默认角色id",position = 3,notes = "角色ID为单选框")
    @Min(value = 1,message = "角色id不能小于1")
    private Long roleId;

    /**
     * 创建方式:0手动，1自动勾选，2自动导入
     */
    @ApiModelProperty("创建方式:0手动，1自动勾选，2自动导入")
    private AccountWay accountWay;

}
