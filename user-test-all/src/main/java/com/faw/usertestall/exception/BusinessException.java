package com.faw.usertestall.exception;

import com.faw.usertestall.domain.common.ErrorCode;

public class BusinessException extends RuntimeException {
    private final String code;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, Throwable throwable) {
        super(throwable);
        this.code = errorCode.getCode();
    }
}
