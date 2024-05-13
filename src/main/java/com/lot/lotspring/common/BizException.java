package com.lot.lotspring.common;

import lombok.Data;

@Data
public class BizException extends RuntimeException {

    private Integer code;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(ResponseStatus responseStatus) {
        super(responseStatus.getMessage());
        this.code = responseStatus.getCode();
    }

}
