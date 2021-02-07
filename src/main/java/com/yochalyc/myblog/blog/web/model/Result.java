package com.yochalyc.myblog.blog.web.model;

import lombok.Data;

@Data
public class Result<T> {

    private String errorCode;
    private String errorMsg;
    private boolean isSuccess = true;
    private T data;

    public Result(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.isSuccess = false;
    }

    public Result(T result) {
        this.data = result;
    }
}
