package com.zjsn.person.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tangjiaren
 * @Date: 2021/6/7 11:49
 */
@Configuration
public class SentinelResourceAspectConfig {
    // 注解支持得配置Bean
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
