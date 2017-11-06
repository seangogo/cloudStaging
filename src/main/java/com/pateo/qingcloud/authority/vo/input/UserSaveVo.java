package com.pateo.qingcloud.authority.vo.input;

import com.pateo.qingcloud.authority.menu.Sex;
import com.pateo.qingcloud.authority.utils.validater.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

@ApiModel( "用户添加输入VO")
@Setter
@Getter
@ToString
public class UserSaveVo {

	@ApiModelProperty( " 用户姓名 ")
	private String realName;

	@ApiModelProperty( " 性别 ")
	private Sex sex;

	@ApiModelProperty( "手机号 ")
	@Phone
	private String phone;

	@ApiModelProperty( " 邮箱 ")
	@Email
	private String email;

	@ApiModelProperty( "  头像url ")
	private String imgUrl;

	@ApiModelProperty(" 账户 ")
	private String UserName;

	@ApiModelProperty("密码")
	private String password;
}
