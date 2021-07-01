package com.zjsn.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @Author: tangjiaren
 * @Date: 2021/6/9 16:10
 */
@Component
@Slf4j
public class ErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("这是一个error过滤器");
        RequestContext currentContext = RequestContext.getCurrentContext();
        Throwable throwable = currentContext.getThrowable();
        log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
        // 一定要有error.status_code key才能返回对应的错误信息回页面
        currentContext.set("error.status_code", HttpStatus.BAD_GATEWAY.value());
        currentContext.set("error.exception", throwable.getCause());
        currentContext.set("error.message", "自定义友好的错误信息");
        return null;
    }
}
