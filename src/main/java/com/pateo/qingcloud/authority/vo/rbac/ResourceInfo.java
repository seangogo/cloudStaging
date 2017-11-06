/**
 * 
 */
package com.pateo.qingcloud.authority.vo.rbac;


import com.pateo.qingcloud.authority.menu.ResourceType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean
 *
 */
@Setter
@Getter
public class ResourceInfo {
	
	/**
	 * 资源ID
	 *
	 * @since 1.0.0
	 */
	private Long id;
	/**
	 *  父资源
	 */
	private Long parentId;
	/**
	 * 资源名
	 *
	 * @since 1.0.0
	 */
	private String name;
	/**
	 * 资源链接
	 *
	 * @since 1.0.0
	 */
	private String link;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 资源类型
	 */
	private ResourceType type;

	/**
	 * 子节点
	 */
	private List<ResourceInfo> children = new ArrayList<>();

}
