package com.shuili.common;


import com.shuili.enums.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @author zeng
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    private String description;


    /**
     * 构造函数
     *
     * @param code        状态码
     * @param data        向前端传的数据
     * @param message     操作反馈信息：如登录成功、查询成功、查询失败、账号密码错误等提示信息。有利于后续纠错。
     * @param description 反馈信息的详细描述
     */
    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    /**
     * 定义各种构造函数，有利于灵活的返回各种信息。如下面这个只返回状态码、数据、操作反馈信息，但不返回详细描述。
     * 而再下一个只返回code和data，不返回message和description的信息。
     */
    public BaseResponse(int code, T data, String message) {
        this(code, data, message, "");
    }

    public BaseResponse(int code, T data) {
        this(code, data, "", "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }

    public BaseResponse(ErrorCode errorCode, String description) {
        this(errorCode.getCode(), null, errorCode.getMessage(), description);
    }

}
