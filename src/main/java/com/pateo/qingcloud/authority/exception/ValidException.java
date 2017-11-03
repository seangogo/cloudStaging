package com.pateo.qingcloud.authority.exception;

import com.pateo.qingcloud.authority.vo.result.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sean
 * 2017/11/3.
 */
@Getter
@Setter
public class ValidException extends RuntimeException {
    private static final long serialVersionUID = -6112780192479692859L;

    private Integer code;

    private String msg;

    public ValidException(Status status) {
        super("user not exist");
        this.code = status.getCode();
        this.msg = status.getDescription();
    }


}
