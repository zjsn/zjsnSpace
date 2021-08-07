package com.zjsn.rolesystem.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjsn.rolesystem.model.to.UserTo;
import com.zjsn.rolesystem.model.user.User;


public interface UserService extends IService<User> {

    void createUser(User user);

    UserTo show(String username);
}
