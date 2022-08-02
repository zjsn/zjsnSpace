package com.zjsn.user.demo.redis;

import org.springframework.stereotype.Service;

@Service
public interface IRedisPostCode {
    String createPostCode(String phone);

    Boolean verifyCode(String phone, String code);
}
