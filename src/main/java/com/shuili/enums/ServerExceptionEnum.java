package com.shuili.enums;


import com.shuili.factory.ExpEnumCodeFactory;

public enum ServerExceptionEnum implements AbstractBaseExceptionEnum {

    /**
     * 当前请求参数为空或数据缺失
     */
    REQUEST_EMPTY(1, "当前请求参数为空或数据缺失，请联系管理员"),

    /**
     * 服务器出现未知异常
     */
    SERVER_ERROR(2, "服务器出现异常，请联系管理员"),

    /**
     * 常量获取存在空值
     */
    CONSTANT_EMPTY(3, "常量获取存在空值，请检查sys_config中是否配置"),

    /**
     * 用户账号不存在
     */
    NO_USERNAME(4,"用户不存在"),

    /**
     * 账号或者密码错误
     */
    ERROR_PWDORUSERNMAE(5,"账号或者密码错误"),

    /**
     * 验证码输入时间过长，请重新获取验证码
     */
    CAPTCHA_EXPIRED(6,"验证码错误"),

    /**
     * 验证码输入错误
     */
    CAPTCHA_ERROR(7,"验证码输入错误，请重新输入")
    ;

    private final Integer code;

    private final String message;

    ServerExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return ExpEnumCodeFactory.getExpEnumCode(this.getClass(), code);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
