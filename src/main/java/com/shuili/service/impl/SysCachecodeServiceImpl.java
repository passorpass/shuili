package com.shuili.service.impl;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuili.entity.SysCachecode;
import com.shuili.mapper.SysCachecodeMapper;
import com.shuili.service.SysCachecodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysCachecodeServiceImpl extends ServiceImpl<SysCachecodeMapper, SysCachecode> implements SysCachecodeService {


    @Resource
    private SysCachecodeMapper cachecodeMapper;


    @Override
    public boolean addCacheCode(String code, String userip) {
        SysCachecode cachecode = new SysCachecode()
                .setCode(code)
                .setUserIp(userip)
                .setCreatTime(DateTime.now())
                .setExperTime(DateTime.now().offset(DateField.MINUTE,3));

        return cachecodeMapper.insert(cachecode) > 0;
    }

    @Override
    public SysCachecode checkCacheCode(Date nowTime, String userIp,String cacheCode) {
        return cachecodeMapper.checkCacheCode(nowTime,userIp,cacheCode);
    }


}
