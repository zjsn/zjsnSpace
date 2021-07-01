//package com.zjsn.zuul.config;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
//import org.springframework.stereotype.Component;
//
///**
// * 处理 filterType 'post' 的时候抛错导致 没有走进 filterType 'error' 的问题
// *
// * @Author: tangjiaren
// * @Date: 2021/6/9 17:11
// */
//@Component
//public class ErrorExtFilter extends SendErrorFilter {
//    @Override
//    public String filterType() {
//        return "error";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 30; // 大于ErrorFilter的值
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        // 判断： 仅处理来自post过滤器引起的异常
//        RequestContext currentContext = RequestContext.getCurrentContext();
//        ZuulFilter failedFilter = (ZuulFilter)currentContext.get("failed.filter");
//        if (ObjectUtil.isNotEmpty(failedFilter) && failedFilter.filterType().equals("post")) {
//            return true;
//        }
//        return false;
//    }
//}
