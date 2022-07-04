package com.zjsn.rolesystem.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjsn.rolesystem.model.to.UserTo;
import com.zjsn.rolesystem.model.user.User;
import com.zjsn.rolesystem.user.mapper.UserMapper;
import com.zjsn.rolesystem.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(User user) {
        // 创建用户
        user.setCreateTime(new Date());
        save(user);
    }

    @Override
    public UserTo show(String username) {

        QueryWrapper<User> wrapper =new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = baseMapper.selectOne(wrapper);
        UserTo userTo = new UserTo();
        BeanUtils.copyProperties(user, userTo);

        return userTo;
    }

    @Override
    public int updateUser(User user) {
        QueryWrapper<User> wrapper =new QueryWrapper<>();
        wrapper.eq("id", user.getId());
        return baseMapper.update(user, wrapper);
    }
}
