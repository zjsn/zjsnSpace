package com.zjsn.person.controller;


import com.zjsn.person.model.Person;
import com.zjsn.person.service.IPersonService;
import com.zjsn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yingluo
 * @since 2021-08-02
 */
@RequestMapping(value = "/person")
@RestController
@RefreshScope
@Api("测试接口")
public class PersonController {

    @Autowired
    private IPersonService personService;


    @ApiOperation(value = "新增")
    @PostMapping("save")
    public R save(@RequestBody Person person) {

        //调用service
        boolean save = personService.save(person);
        if (save) {
            return new R(200,"成功");
        } else {
            return new R(500,"失败");
        }
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public R removeHospSet(@PathVariable Long id) {
        boolean flag = personService.removeById(id);
        if (flag) {
            return new R(200,"成功");
        } else {
            return new R(500,"失败");
        }
    }

    @ApiOperation(value = "修改")
    @PostMapping("update")
    public R update(@RequestBody Person person) {
        boolean flag = personService.updateById(person);
        if (flag) {
            return new R(200,"成功");
        } else {
            return new R(500,"失败");
        }
    }

    @ApiOperation(value = "详情")
    @GetMapping("get/{id}")
    public R get(@PathVariable Long id) {
        Person person = personService.getById(id);
        return new R(200,"成功",person);
    }
}
