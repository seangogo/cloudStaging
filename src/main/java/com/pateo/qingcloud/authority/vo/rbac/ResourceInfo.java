/**
 * 
 */
package com.pateo.qingcloud.authority.vo.rbac;


import com.pateo.qingcloud.authority.menu.ResourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sean
 *
 */
@Setter
@Getter
@ApiModel("资源详情VO")
public class ResourceInfo {

	@ApiModelProperty("资源id")
	private String id;

	@ApiModelProperty("资源名称 如xx菜单，xx按钮")
	private String name;

	@ApiModelProperty("资源编码 如user_save")
	private String code;

	@ApiModelProperty("资源类型 0=菜单 1=按钮")
	private ResourceType type;

	@ApiModelProperty("实际需要控制权限的url集合")
	private Set<String> urls;

	@ApiModelProperty("资源链接")
	@NotNull
	private String link;

	/**
	 *  父资源
	 */
	private Long parentId;

	/**
	 * 图标
	 */
	private String icon;


	/**
	 * 子节点
	 */
	private List<ResourceInfo> children = new ArrayList<>();

}
