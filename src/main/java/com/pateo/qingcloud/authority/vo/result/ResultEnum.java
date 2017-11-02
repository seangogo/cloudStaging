package com.pateo.qingcloud.authority.vo.result;

import lombok.Getter;

/**
 *
 * @author sean
 * @date 2017/9/11
 */
@Getter
public enum ResultEnum{
    SUCCESS(200, "Success"),
    PARAM_ERROR(1, "参数不正确"),
    SERVICE_RESPONSE_TIMEOUT(407,"Time out"),
    SERVICE_RESPONSE_INVALID(401,"Invalid Parameters"),
    SERVICE_RESPONSE_SERVICE_DISABLED(404,"The service has been disabled"),
    SERVICE_RESPONSE_ERROR_CODE(500,"Inner server errors"),
    FEIGN_CLIENT_ERROR(20010,"目标服务停止工作"),
    DATA_NOT_EXIST(402,"No data info is found"),
    Class_NOT_EXIST(403,"No data info is found"),
    ID_NOT_EXIST(399,"No data info is found"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
