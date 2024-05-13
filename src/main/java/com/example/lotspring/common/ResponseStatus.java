package com.example.lotspring.common;

import cn.hutool.http.HttpStatus;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public enum ResponseStatus {

    FORBIDDEN(HttpStatus.HTTP_FORBIDDEN,  "Forbidden"),
    UNAUTHORIZED(HttpStatus.HTTP_UNAUTHORIZED, "Unauthorized"),
    SUCCESS(HttpStatus.HTTP_OK, "SUCCESS"),
    BAD_REQUEST(HttpStatus.HTTP_BAD_REQUEST, "Bad Request"),
    SYSTEM_ERROR(HttpStatus.HTTP_INTERNAL_ERROR, "System Error"),
    NOT_EXIST(HttpStatus.HTTP_NOT_FOUND, "Request Not Found")
    ;


    /**
     * 返回的HTTP状态码,  符合http请求
     */
    private HttpStatus httpStatus;
    /**
     * 业务异常码
     */
    private final Integer code;
    /**
     * 业务异常信息描述
     */
    private final String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
