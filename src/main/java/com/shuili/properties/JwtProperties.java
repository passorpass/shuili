package com.shuili.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtProperties {

    /**
     * 管理端员工生成jwt令牌相关配置
     */
    private String adminSecretKey = "shuili";
    private long adminTtl = 7200000;
    private String adminTokenName = "Authorization";

    /**
     * 用户端微信用户生成jwt令牌相关配置
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

}
