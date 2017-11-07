package com.pateo.qingcloud.authority.vo.result;

/**
 *
 * @author sean
 * @date 2017/11/7
 */

import com.pateo.qingcloud.authority.menu.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sean
 * @date 2017/10/20
 */
@Getter
@Setter
@ApiModel
public class StateResult{
    @ApiModelProperty("返回码")
    protected String code;
    @ApiModelProperty("返回码描述")
    protected String description;

    public static StateResult success() {
        return  new StateResult(ResultEnum.SUCCESS);
    }

    public static StateResult error(ResultEnum resultEnum) {
        return new StateResult(resultEnum);
    }


    public StateResult(String code, String description) {
        this.code = code;
        this.description = description;
    }
    public StateResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.description = resultEnum.getMessage();
    }
    public StateResult() {
    }
}
