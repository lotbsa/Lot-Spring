package com.lot.lotspring.common;

import lombok.Getter;

@Getter
public class ResponseVO<T> {

    private Integer code;

    private String message;

    private T data;

    public ResponseVO() {

    }

    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVO(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ResponseVO(ResponseStatus responseStatus, T data) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
        this.data = data;
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static ResponseVO<Void> success() {
        return new ResponseVO<>(ResponseStatus.SUCCESS, null);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<>(ResponseStatus.SUCCESS, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> ResponseVO<T> success(ResponseStatus responseStatus, T data) {
        if (responseStatus == null) {
            return success(data);
        }
        return new ResponseVO<>(responseStatus, data);
    }

    /**
     * 业务异常返回业务代码和描述信息
     */
    public static <T> ResponseVO<T> failure() {
        return new ResponseVO<>(ResponseStatus.SYSTEM_ERROR, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResponseVO<T> failure(ResponseStatus responseStatus) {
        return failure(responseStatus, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResponseVO<T> failure(ResponseStatus responseStatus, T data) {
        if (responseStatus == null) {
            return new ResponseVO<>(ResponseStatus.SYSTEM_ERROR, null);
        }
        return new ResponseVO<>(responseStatus, data);
    }

    public static <T> ResponseVO<T> failure(Integer code, String message) {
        return new ResponseVO<>(code, message);
    }

}
