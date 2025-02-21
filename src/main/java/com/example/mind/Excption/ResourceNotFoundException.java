package com.example.mind.Excption;


public class ResourceNotFoundException extends RuntimeException {

    // 构造函数，可以根据需求传递错误信息和其他参数
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
