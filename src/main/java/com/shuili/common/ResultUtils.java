package com.shuili.common;


import com.shuili.enums.ErrorCode;

public class ResultUtils {

    /**
     * 成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static BaseResponse success(int data) {
        return new BaseResponse(0, data, "ok");
    }

    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription())   ;
    }

    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description){
        return new BaseResponse<>(errorCode.getCode(),description)   ;
    }

    /**
     * 失败
     * @param code
     * @return
     */
    public static BaseResponse error(int code,String message,String description){
        return new BaseResponse<>(code,null,message,description)   ;
    }
    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode,String description){
        return new BaseResponse<>(errorCode.getCode(),null,errorCode.getMessage(),description)   ;
    }

}
