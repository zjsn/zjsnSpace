package com.zjsn.domain.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tangjiaren
 * @Date: 2021/6/11 11:31
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "module")
public class module {
    /**
     *  注册中心应用名字
     */
    private String registry;
    /**
     *  用户中心应用名字
     */
    private String userCenter;
    /**
     *  权限中心应用名字
     */
    private String authorityCenter;
}
