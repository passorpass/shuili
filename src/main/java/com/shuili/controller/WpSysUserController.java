package com.shuili.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuili.common.ErrorResponseData;
import com.shuili.common.ResponseData;
import com.shuili.common.SuccessResponseData;
import com.shuili.dto.LoginForm;
import com.shuili.dto.TokenInfo;
import com.shuili.entity.SysCachecode;
import com.shuili.entity.WpSysUser;
import com.shuili.enums.ServerExceptionEnum;
import com.shuili.properties.JwtProperties;
import com.shuili.service.SysCachecodeService;
import com.shuili.service.WpSysUserService;
import com.shuili.util.EncryUtil;
import com.shuili.util.HttpServletUtil;
import com.shuili.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class WpSysUserController extends BaseController {



    //用户业务层
    @Resource
    private WpSysUserService userService;

    //token签名
    @Resource
    private JwtProperties jwtProperties;

    //验证码业务层
    @Resource
    private SysCachecodeService cachecodeService;

    /**
     * @author huhu
     * @date 2024-03-07 15:20:50
     * 用户登录
     */
    @PostMapping("/login")
    public ResponseData UserLogin(@RequestBody LoginForm form){
        String username = form.getName();
        String password = form.getPwd();
        String captcha = form.getCaptcha();
        Date nowTime = form.getNowTime();

        log.info("用户名：{}",username);
        log.info("密码：{}",password);
        log.info("验证码:{}",captcha);
        log.info("当前时间:{}",nowTime);

        //开启数据库查询
        LambdaQueryWrapper<WpSysUser> lqw = new LambdaQueryWrapper<WpSysUser>();
        lqw.eq(WpSysUser::getUsername,username);
        //查出对应用户的实体类
        WpSysUser user = userService.getOne(lqw);
        //拿到实体先判是否没空
        if(ObjectUtil.isEmpty(user)){
            return ErrorResponseData.error(ServerExceptionEnum.NO_USERNAME);
        }
        //如果存在获取用户的密码和账号
        String userName = user.getUsername();
        String passWord = user.getPassword();

        //判断账号和密码是否一致
        if(!userName.equals(username) && !EncryUtil.comparePasswords(password,passWord)){
            return ErrorResponseData.error(ServerExceptionEnum.ERROR_PWDORUSERNMAE);
        }

        //判断验证输入是否正确
        //1.获取存储的验证码信息并且验证验证码是否过期
        SysCachecode sysCachecodes = cachecodeService.checkCacheCode(nowTime, getUserIp(),captcha);
        if(ObjectUtil.isEmpty(sysCachecodes)){
            return ErrorResponseData.error(ServerExceptionEnum.CAPTCHA_EXPIRED);
        }
        //验证用户验证码是否输入正确
        if(!sysCachecodes.getCode().equals(captcha)){
            return ErrorResponseData.error(ServerExceptionEnum.CAPTCHA_ERROR);
        }
        //生成用户token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        log.info("token ==>{}",token);
        TokenInfo tokeninfo = new TokenInfo().setUserName(userName).setToken(token);

        setUserId(user.getId());
        log.info("用户id:{}",getUserId());
        //登录成功,返还令牌信息
        return SuccessResponseData.success(tokeninfo);

    }


    /**
     * @author huhu
     * @date 2024-03-06 20:01:07
     *
     * 获取验证码
     */
    @GetMapping("/getCaptcha")
    public void getCaptcha() throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(130, 40, 5, 4);
        HttpSession session = HttpServletUtil.getRequest().getSession();
        captcha.setBackground(Color.getHSBColor(0.5409357f, 0.24782608f, 0.9019608f));
        session.setAttribute("CAPTCHA_SESSION_KEY", captcha.getCode());
        HttpServletResponse response = HttpServletUtil.getResponse();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLUtil.encode("captcha.jpg") + "\"");
        response.addHeader("Content-Length", "" + captcha.getImageBytes().length);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        captcha.write(outputStream);
        //保存在数据库中
        cachecodeService.addCacheCode(captcha.getCode(),getUserIp());
        log.info("获取验证码:{}",captcha.getCode());
    }



    @GetMapping("/all")
    public ResponseData getallAdmin(){
        List<WpSysUser> list = userService.list();
        return SuccessResponseData.success(list);
    }

}
