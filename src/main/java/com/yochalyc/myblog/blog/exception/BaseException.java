package com.yochalyc.myblog.blog.exception;

public class BaseException extends RuntimeException {

    protected String errorCode;

    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(ErrorCode code) {
        super("[" + code.getCode() + "]" + code.getDesc());
        this.errorCode = code.getCode().toString();
    }

}
