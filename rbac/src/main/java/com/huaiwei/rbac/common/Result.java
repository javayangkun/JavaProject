package com.huaiwei.rbac.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    public static Integer OK = 0;
    public static Integer ERROR = 500;
    private Integer code;
    private boolean success;
    private long count;
    private String msg;
    private Object data;

    private Result() {
    }


    public static Result SUCCESS() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(Result.OK);
        r.setMsg("成功");
        return r;
    }

    public static Result FAILURE() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(Result.ERROR);
        r.setMsg("失败");
        return r;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public Result count(long count) {
        this.count = count;
        return this;
    }

    public Result success(boolean success) {
        this.success = success;
        return this;
    }

    public Result code(Integer code) {
        this.code = code;
        return this;
    }

    public Result message(String msg) {
        this.msg = msg;
        return this;
    }


}
