package com.zjsn.user.controller;

import com.zjsn.domain.user.UserMongoEntity;
import com.zjsn.user.demo.redis.IRedisDelBigKeys;
import com.zjsn.user.demo.redis.IRedisPostCode;
import com.zjsn.user.demo.signDemo.*;
import com.zjsn.user.feign.feignHelper.RegistryHelper;
import com.zjsn.user.service.IGoodsSecKillService;
import com.zjsn.user.service.TicketSaleService;
import com.zjsn.user.service.UserMongoService;
import com.zjsn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.zjsn.user.demo.redis.IRedisPostCodeImpl.getCode;

/**
 * @Author: tangjiaren
 * @Date: 2021/5/26 15:39
 */
@RequestMapping(value = "/apiController", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @Resource
    GoodsName1 goodsName1;
    @Resource
    GoodsName2 goodsName2;
    @Resource
    AllQueryGoodsFun allQueryGoodsFun;
    @Resource
    Decorator decorator;
    @Resource
    IRedisPostCode iRedisPostCode;
    @Resource
    IGoodsSecKillService iGoodsSecKillService;
    @Resource
    IRedisDelBigKeys iRedisDelBigKeys;

    @ApiOperation(value = "说你好")
    @ApiImplicitParam(name = "name", value = "名称", dataTypeClass = String.class)
    @GetMapping(value = "/sayHello")
    public String sayHello(String name) {
        String hello = "yokoso,UserCenter,Mr." + name;
        return hello;
    }

    @ApiOperation(value = "访问注册中心接口")
    @ApiImplicitParam(name = "name", value = "名称", dataTypeClass = String.class)
    @GetMapping(value = "/visitRegistry")
    public String visitRegistry(String name) {
        return registryHelper.visitRegistry(name);
    }

    @ApiOperation(value = "保存mongo")
    @ApiImplicitParam(name = "userMongoEntity", value = "mongoUser实体类", dataTypeClass = UserMongoEntity.class)
    @PostMapping(value = "/createUser")
    public UserMongoEntity createUser(@RequestBody UserMongoEntity userMongoEntity) {
        return userMongoService.save(userMongoEntity);
    }

    @ApiOperation(value = "查看nacos配置")
    @GetMapping(value = "/findProjectName")
    public String findProjectName() {
        System.out.println(newTitle);
        return this.title;
    }

    @ApiOperation(value = "测试售卖门票")
    @GetMapping(value = "/testSaleTicket")
    public String testSaleTicket(@RequestParam(value = "tickets", required = false) Integer tickets) throws InterruptedException {
        return ticketSaleService.sale(tickets);
    }

    @ApiOperation(value = "重制门票")
    @GetMapping(value = "/reseatTicket")
    public String reseatTicket() {
        return ticketSaleService.reseatTicket();
    }

    @ApiOperation(value = "商品查询1")
    @GetMapping(value = "/queryGoodsName1")
    public void queryGoodsName1()  {
        goodsName1.queryGoodsMethod1();
    }

    @ApiOperation(value = "商品查询2")
    @GetMapping(value = "/queryGoodsName2")
    public void queryGoodsName2()  {
        goodsName2.queryGoodsMethod2();
    }
    @ApiOperation(value = "商品查询1")
    @GetMapping(value = "/queryGoodsName3")
    public void queryGoodsName3()  {
        allQueryGoodsFun.startQuery();
    }

    @ApiOperation(value = "商品查询2")
    @GetMapping(value = "/queryGoodsName4")
    public void queryGoodsName4()  {
        decorator.queryGoodsMethod1();
    }

    @ApiOperation(value = "生成验证码")
    @GetMapping(value = "/createPostCode")
    public R<String> createPostCode(@RequestParam("phone") String phone)  {
        return R.ok(iRedisPostCode.createPostCode(phone));
    }

    @ApiOperation(value = "校验验证码")
    @GetMapping(value = "/verifyCode")
    public R<Boolean> verifyCode(@RequestParam("phone") String phone, @RequestParam("code") String code)  {
        return R.ok(iRedisPostCode.verifyCode(phone, code));
    }

    @ApiOperation(value = "校验验证码")
    @GetMapping(value = "/doSecKill")
    public R<Boolean> doSecKill(@RequestParam("prodId") String prodId)  {
        return R.ok(iGoodsSecKillService.doSecKill(getCode(), prodId));
    }

    @ApiOperation(value = "补充秒杀商品")
    @GetMapping(value = "/supplementGoodsCount")
    public R<Boolean> supplementGoodsCount(@RequestParam("prodId") String prodId, @RequestParam("num") Integer num)  {
        iGoodsSecKillService.supplementGoodsCount(num, prodId);
        return R.ok();
    }

    @ApiOperation(value = "删除key")
    @GetMapping(value = "/delBigHash")
    public R<Boolean> delBigHash(@RequestParam("bigKey") String bigKey)  {
        iRedisDelBigKeys.delBigZSet(bigKey);
        return R.ok();
    }
}
