package com.shuili.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shuili.entity.SysCachecode;

import java.util.Date;

public interface SysCachecodeService extends IService<SysCachecode> {

    //保存验证码
    boolean addCacheCode(String code,String userip);

    //检查验证码是否过期
    SysCachecode checkCacheCode(Date nowTime,String userIp,String cacheCode);

}
