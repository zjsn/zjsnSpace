package com.zjsn.registry.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zjsn.registry.feign.feignHelper.UserHelper;

/**
 * @Author: tangjiaren
 * @Date: 2021/5/26 15:30
 */
@RestController
@RequestMapping(value = "/apiController")
@Slf4j
public class ApiController {

    @Autowired
    private UserHelper userHelper;

    @ApiOperation(value = "说你好")
    @ApiImplicitParam(name = "name", value = "名称", dataTypeClass = String.class)
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(String name) {
        String hello = "yokoso,registry,Mr." + name;
        return hello;
    }

    @ApiOperation(value = "访问注册中心接口")
    @ApiImplicitParam(name = "name", value = "名称", dataTypeClass = String.class)
    @RequestMapping(value = "/visitUserCenter", method = RequestMethod.GET)
    public String visitUserCenter(String name) {
        return userHelper.visitUserCenter(name);
    }
}
