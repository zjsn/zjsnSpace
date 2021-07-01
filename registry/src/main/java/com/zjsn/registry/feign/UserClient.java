package com.zjsn.registry.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: tangjiaren
 * @Date: 2021/5/26 16:59
 */
@FeignClient("${module.userCenter}")
public interface UserClient {

    @GetMapping("/user/apiController/hello")
    String visitUserCenter(@RequestParam String name);
}
