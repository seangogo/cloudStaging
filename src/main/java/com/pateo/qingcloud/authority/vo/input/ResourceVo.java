package com.pateo.qingcloud.authority.vo.input;

import com.pateo.qingcloud.authority.menu.ResourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 *
 * @author sean
 * @date 2017/11/7
 */
@ApiModel("新增/修改资源VO")
@Getter
@Setter
public class ResourceVo {
    @ApiModelProperty("资源id :不传ID传空白字符串都为新增")
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

    @ApiModelProperty("父资源主键")
    @Range(min = 36,max = 36,message = "数据有误")
    private String parent;

    @ApiModelProperty("图标样式")
    private String icon;

    @ApiModelProperty("简介")
    @Range(max = 150,message = "简介不能超过150")
    private String remark;

}
