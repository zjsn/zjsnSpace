package com.zjsn.user.demo.redis;

import org.springframework.stereotype.Service;

@Service
public interface IRedisDelBigKeys {

    void delBigHash(String bigHashKey);

    void delBigList(String bigListKey);

    void delBigSet(String bigSetKey);

    void delBigZSet(String bigZSetKey);

}
