package com.shuili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuili.entity.WpSysUser;
import com.shuili.mapper.WpSysUserMapper;
import com.shuili.service.WpSysUserService;
import org.springframework.stereotype.Service;

@Service
public class WpSysUserServiceImpl extends ServiceImpl<WpSysUserMapper, WpSysUser> implements WpSysUserService  {
}
