package com.zjsn.zuul.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zjsn.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tangjiaren
 * @Date: 2021/6/9 9:53
 */
@Configuration
@Slf4j
public class AccessFilter extends ZuulFilter {
    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：可以在请求被路由之前调用
     * routing：在路由请求时候被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String url = request.getRequestURL().toString();
        log.info(String.format("%s request to %s",request.getMethod(), url));
        // 判断参数是否拥有accessToken参数
        String accessToken = request.getParameter("accessToken");
        // 如果是swagger的话就不需要验证
        if (url.contains("/v2/api-docs")) {
            accessToken = "true";
        }
        // 如果为空的话就不放行
        // todo accessToken 可以匹配对应的数据库的数据
        if (StrUtil.isBlank(accessToken)) {
            log.warn("access token is empty");
            R r = R.build(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            String body = JSONObject.toJSONString(r, SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames);
            currentContext.setResponseBody(body);
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
