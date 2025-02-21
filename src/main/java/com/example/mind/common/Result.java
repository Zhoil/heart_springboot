package com.example.mind.common;

public class Result {
    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static Result error(int code, String message) {
        return new Result(code, message, null);
    }

    public static Result error(int code, String message, Object data) {
        return new Result(code, message, data);
    }

    public static Result error(String message) {
        return new Result(500, message, null);
    }

    public static Result error(String message, Object data) {
        return new Result(500, message, data);
    }

    public static Result error() {
        return new Result(500, "error", null);
    }

    public static Result error(Object data) {
        return new Result(500, "error", data);
    }

    public static Result error(int code) {
        return new Result(code, "error", null);
    }

    public static Result error(int code, Object data) {
        return new Result(code, "error", data);
    }


}
