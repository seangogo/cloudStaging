package com.pateo.qingcloud.authority.vo.result;

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
public class Status<T> {
    @ApiModelProperty("返回码")
    protected Integer code;
    @ApiModelProperty("返回码描述")
    protected String description;

    @ApiModelProperty("具体内容")
    private T data;

    public static Status success(Object object) {
        return  new Status(ResultEnum.SUCCESS,object);
    }

    public static Status success() {
        return success(null);
    }

    public static Status error(ResultEnum resultEnum) {
        return new Status(resultEnum);
    }

    public Status(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.description = resultEnum.getMessage();
        this.data = data;
    }
    public Status(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.description = resultEnum.getMessage();
    }

    public Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Status(Integer code, String description, T data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public Status() {
    }
}
