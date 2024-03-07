package com.shuili.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Slf4j
public class CorsConfig {


    @Bean
    public CorsFilter corsFilter() {
        log.info("Cors开启");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        //设置预检请求有效期 30min
        corsConfiguration.setMaxAge(1800L);
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
        corsConfiguration.addExposedHeader("token");
        return new CorsFilter(source);
    }
}
