package com.shuili.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    //登录账号
    private String name;
    //登录密码
    private String pwd;
    //登录验证码
    private String captcha;
    //点击登录的时间
    private Date nowTime;

}
