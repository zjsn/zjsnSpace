package com.zjsn.rolesystem.user.controller;

import com.zjsn.rolesystem.model.user.User;
import com.zjsn.rolesystem.user.service.UserService;
import com.zjsn.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yingluo
 * @since 2021-08-05
 */
@RequestMapping(value = "/user")
@RestController
@RefreshScope
@Api("用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户")
    @PostMapping("save")
    public R addUser(@RequestBody User user) {
        //调用service
        userService.createUser(user);

        return R.ok();
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("update")
    public R updateUser(@RequestBody User user) {
        //调用service
        userService.updateById(user);

        return R.ok();
    }

    @ApiOperation(value = "删除用户")
    @GetMapping("delete/{id}")
    public R deleteUser(@PathVariable String id) {
        //调用service
        userService.removeById(id);

        return R.ok();
    }

    @ApiOperation(value = "查找用户")
    @GetMapping("findByName/{username}")
    public R findByName(@PathVariable String username) {
        return R.ok(userService.show(username));
    }


}
