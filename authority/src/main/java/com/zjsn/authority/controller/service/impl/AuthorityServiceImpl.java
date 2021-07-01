package com.zjsn.authority.controller.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zjsn.authority.controller.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: tangjiaren
 * @Date: 2021/6/1 11:17
 */
@Service
@Slf4j
public class AuthorityServiceImpl implements AuthorityService {

    @Override
    @SentinelResource(value = "authorityService-sayHello", blockHandler = "sayHelloExceptionHandle")
    public String sayHello(String name) {
        return "hello," + name;
    }

    @Override
    @SentinelResource(value = "authorityService-authorityOpen", fallback = "authorityOpenFallback")
    public String authorityOpen(String msg) {
        if (StrUtil.isBlank(msg)) {
            throw new RuntimeException("日常接口出错");
        }
        return msg;
    }

    public String authorityOpenFallback(String msg) {
        log.error("fallbackHandle:" + msg);
        return "这是接口熔断的回调方法";
    }

    public String sayHelloExceptionHandle(String name, BlockException exception) {
        log.error("接口堵塞了." + name, exception);
        return "系统繁忙,请稍后再试!";
    }
}
