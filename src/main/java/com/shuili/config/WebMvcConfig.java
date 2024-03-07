package com.shuili.config;


import com.shuili.interceptor.JwtTokenAdminInterceptor;
import com.shuili.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {



    @Resource
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    /**
     * 扩展spring mvc框架的消息转化器
     *
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        log.info("扩展消息转化器...");
        MappingJackson2HttpMessageConverter mp = new MappingJackson2HttpMessageConverter();

        //java序列化为json数据
        mp.setObjectMapper(new JacksonObjectMapper());
        //将自己的消息转化器加入到容器中
        //排序 0
        converters.add(0, mp);

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/**") // 添加需要拦截的路径，这里是所有路径
                .excludePathPatterns("/user/login", "/user/getCaptcha"); // 排除不需要拦截的路径，例如登录和注册
    }


}
