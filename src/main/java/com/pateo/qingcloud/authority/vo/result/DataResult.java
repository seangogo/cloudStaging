package com.pateo.qingcloud.authority.vo.result;

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
public class DataResult<T> {
    @ApiModelProperty("返回码")
    protected String code;
    @ApiModelProperty("返回码描述")
    protected String description;

    @ApiModelProperty("具体内容")
    private T data;

    public static DataResult success(Object object) {
        return  new DataResult(ResultEnum.SUCCESS,object);
    }

    public DataResult(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.description = resultEnum.getMessage();
        this.data = data;
    }

    public DataResult() {
    }
}
