package com.shuili.enums;
/**
 * 返回码
 *
 * @author zeng
 */
public enum ErrorCode {

    SUCCESS(200,"ok","") ,
    PARAMS_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"请求参数为空",""),
    NO_LOGIN(40100,"未登录",""),
    NO_AUTH(40101,"暂无权限访问",""),
    SYSTEM_ERROR(50000,"系统内部异常",""),
    ERROR_PWDORUSER(1001,"登录账号或者密码错误",""),
    ERROR_CODE(10001,"验证码错误","")
    ;
    //返回码
    private final int code;
    //操作响应信息
    private final String message;
    //响应信息的详细描述
    private final String description;

    //构造函数
    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
    //get方法
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
