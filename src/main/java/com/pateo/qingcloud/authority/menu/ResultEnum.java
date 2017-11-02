package com.pateo.qingcloud.authority.menu;

import lombok.Getter;

/**
 * Created by sean on 2017/9/11.
 */
@Getter
public enum ResultEnum {
    SUCCESS(200, "Success"),
    PARAM_ERROR(1, "参数不正确"),
    SERVICE_RESPONSE_TIMEOUT(407,"Time out"),
    SERVICE_RESPONSE_INVALID(401,"Invalid Parameters"),
    SERVICE_RESPONSE_SERVICE_DISABLED(404,"The service has been disabled"),
    SERVICE_RESPONSE_ERROR_CODE(500,"Inner server errors"),
    FEIGN_CLIENT_ERROR(20010,"目标服务停止工作"),
    DATA_NOT_EXIST(402,"No data info is found"),
    OBJ_NOT_EXIST(10, "对象不存在"),
    CODE_ERROR(1001, "验证码不正确"),
    EMAIL_EXIST(1002,"帐号|Email已经存在！"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
