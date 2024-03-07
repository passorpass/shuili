package com.shuili.controller;


import com.shuili.context.BaseContext;
import com.shuili.entity.WpSysUser;
import com.shuili.service.WpSysUserService;
import com.shuili.util.HttpServletUtil;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BaseController {

    @Resource
    private WpSysUserService userService;

    public static long getUserId(){
        return BaseContext.getCurrentId();
    }

    public static void setUserId(long id){
        BaseContext.setCurrentId(id);
    }

    public String getUserName(long id){
        WpSysUser user = userService.getById(id);
        return user.getUsername();
    }

    public static String getUserIp(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setSessionId(long id){
        HttpServletUtil.getRequest().setAttribute("userId",id);
    }

    public static long getSessionId(){
       return (long) HttpServletUtil.getRequest().getAttribute("userId");
    }

}
