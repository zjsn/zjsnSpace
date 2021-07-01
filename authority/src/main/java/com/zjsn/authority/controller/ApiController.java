package com.zjsn.authority.controller;

import com.zjsn.authority.controller.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tangjiaren
 * @Date: 2021/6/1 11:10
 */
@RestController
@RequestMapping(value = "/apiController")
@RefreshScope
@Api("权限公共接口")
public class ApiController {
    @Autowired
    private AuthorityService authorityService;

    @ApiOperation(value="hello接口")
    @ApiImplicitParam(name = "name", value = "名称", dataTypeClass = String.class)
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return authorityService.sayHello(name);
    }

    @ApiOperation(value="开通权限")
    @ApiImplicitParam(name = "msg", value = "信息", dataTypeClass = String.class)
    @RequestMapping(value = "/openAuthority", method = RequestMethod.GET)
    public String openAuthority(@RequestParam String msg) {
        return authorityService.authorityOpen(msg);
    }

}
