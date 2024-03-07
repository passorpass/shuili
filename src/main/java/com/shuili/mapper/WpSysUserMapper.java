package com.shuili.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuili.entity.WpSysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WpSysUserMapper extends BaseMapper<WpSysUser> {

    @Override
    List<Map<String, Object>> selectMaps(Wrapper<WpSysUser> queryWrapper);


}
