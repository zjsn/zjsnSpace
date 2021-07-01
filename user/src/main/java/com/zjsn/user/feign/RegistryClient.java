package com.zjsn.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: tangjiaren
 * @Date: 2021/5/26 16:19
 */
@FeignClient("${module.registry}")
public interface RegistryClient {

    @GetMapping("/registry/apiController/hello")
    String visitRegistry(@RequestParam String name);
}
