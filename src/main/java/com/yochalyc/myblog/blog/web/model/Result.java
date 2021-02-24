package com.yochalyc.myblog.blog.web.model;

import lombok.Data;

@Data
public class Result<T> {

    private String code;
    private String msg;
    private boolean status = true;
    private T data;

    public Result(String errorCode, String errorMsg) {
        this.code = errorCode;
        this.msg = errorMsg;
        this.status = false;
    }

    public Result(T result) {
        this.data = result;
    }

}
