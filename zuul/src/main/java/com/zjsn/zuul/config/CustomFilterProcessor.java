//package com.zjsn.zuul.config;
//
//import com.netflix.zuul.FilterProcessor;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
///**
// * 自定义filterProcessor 用于记录抛错时对应的filter类型
// *
// * @Author: tangjiaren
// * @Date: 2021/6/9 17:01
// */
//@Slf4j
//@Component
//public class CustomFilterProcessor extends FilterProcessor {
//    @Override
//    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
//        try {
//            return super.processZuulFilter(filter);
//        } catch (Throwable var15) {
//            RequestContext currentContext = RequestContext.getCurrentContext();
//            currentContext.set("failed.filter", filter);
//            throw var15;
//        }
//    }
//}
