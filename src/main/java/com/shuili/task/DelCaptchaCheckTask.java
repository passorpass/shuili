package com.shuili.task;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuili.entity.SysCachecode;
import com.shuili.service.SysCachecodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huhu
 * @date 2024-03-07 17:58:07
 * 定时删除已过期的验证码
 */
@Component
@Slf4j
public class DelCaptchaCheckTask {


    @Resource
    private SysCachecodeService cachecodeService;

    @Bean
    public void taskStar(){
        log.info("定时任务启动中..........");

    }

    // 每隔5分钟执行一次
    @Scheduled(fixedRate = 30 * 60 * 1000) // 5分钟
    public void deleteExpiredCaptchaCodes() {
        log.info("=============================定时任务(清除过期验证码中)==========================================");
        //判断验证码是否过期
        List<SysCachecode> cachecodeList = cachecodeService.list(new LambdaQueryWrapper<SysCachecode>().lt(SysCachecode::getExperTime, DateTime.now()));
        log.info("=========================（已过期的验证码个数："+cachecodeList.size()+"）==============================");
        if(cachecodeList.size() > 0) {
            boolean flag = cachecodeService.remove(new LambdaQueryWrapper<SysCachecode>().lt(SysCachecode::getExperTime, DateTime.now()));
            log.info("清除过期验证码：{}个，过期验证码清除状态:{},", cachecodeList.size(), flag);
        }else {
            log.info("===============================没有过期的验证码=========================");
        }
    }
}
