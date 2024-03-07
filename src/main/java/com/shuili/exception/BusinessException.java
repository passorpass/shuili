package com.shuili.exception;




/**
 * 业务异常类
 * 继承RuntimeException异常处理类。
 */
public class BusinessException extends RuntimeException{
    private  int code;
    private  String description;

    /**
     * 各种构造函数，供我们灵活的使用
     */
    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }


    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
