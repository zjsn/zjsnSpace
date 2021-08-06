package com.zjsn.rolesystem.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjsn.rolesystem.model.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
