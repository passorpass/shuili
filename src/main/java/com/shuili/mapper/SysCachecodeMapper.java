package com.shuili.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuili.entity.SysCachecode;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
* @author lenovo
* @description 针对表【sys_cachecode(临时验证码)】的数据库操作Mapper
* @createDate 2024-03-07 16:18:53
* @Entity generator.domain.SysCachecode
*/

@Mapper
public interface SysCachecodeMapper extends BaseMapper<SysCachecode> {



    //检查验证码是否过期
    SysCachecode checkCacheCode(Date nowTime, String userIp, String cacheCode);

}
