package com.zjsn.user.controller;

import com.zjsn.domain.user.UserMongoEntity;
import com.zjsn.user.feign.feignHelper.RegistryHelper;
import com.zjsn.user.service.TicketSaleService;
import com.zjsn.user.service.UserMongoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tangjiaren
 * @Date: 2021/5/26 15:39
 */
@RequestMapping(value = "/apiController")
@RestController
@RefreshScope
@Api("用户公共接口")/*tags 可以定义多个,里面包含多个里面的所有接口*/
public class ApiController {

    @Value("${zjsnSpace.title}")
    private String title;
    @Value("${zjsn.space-title}")
    private String newTitle;

    @Autowired
    private RegistryHelper registryHelper;
    @Autowired
    private UserMongoService userMongoService;
    @Autowired
    private TicketSaleService ticketSaleService;

    @ApiOperation(value = "说你好")
    @ApiImplicitParam(name = "name", value = "名称", dataTypeClass = String.class)
    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(String name) {
        String hello = "yokoso,UserCenter,Mr." + name;
        return hello;
    }

    @ApiOperation(value = "访问注册中心接口")
    @ApiImplicitParam(name = "name", value = "名称", dataTypeClass = String.class)
    @RequestMapping(value = "/visitRegistry", method = RequestMethod.GET)
    public String visitRegistry(String name) {
        return registryHelper.visitRegistry(name);
    }

    @ApiOperation(value = "保存mongo")
    @ApiImplicitParam(name = "userMongoEntity", value = "mongoUser实体类", dataTypeClass = UserMongoEntity.class)
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public UserMongoEntity createUser(@RequestBody UserMongoEntity userMongoEntity) {
        return userMongoService.save(userMongoEntity);
    }

    @ApiOperation(value = "查看nacos配置")
    @RequestMapping(value = "/findProjectName", method = RequestMethod.GET)
    public String findProjectName() {
        System.out.println(newTitle);
        return this.title;
    }

    @ApiOperation(value = "测试售卖门票")
    @RequestMapping(value = "/testSaleTicket", method = RequestMethod.GET)
    public void testSaleTicket() {
        ticketSaleService.sale();
    }
}
