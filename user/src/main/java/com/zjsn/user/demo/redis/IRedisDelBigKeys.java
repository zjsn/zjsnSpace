package com.zjsn.user.demo.redis;

import org.springframework.cache.annotation.CacheConfig;

@CacheConfig(cacheNames = "users")
public interface IRedisDelBigKeys {

    void delBigHash(String bigHashKey);

    void delBigList(String bigListKey);

    void delBigSet(String bigSetKey);

    void delBigZSet(String bigZSetKey);

}
