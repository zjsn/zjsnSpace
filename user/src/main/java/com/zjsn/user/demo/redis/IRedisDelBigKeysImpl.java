package com.zjsn.user.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;


@CacheConfig(cacheNames = "users")
public class IRedisDelBigKeysImpl implements IRedisDelBigKeys{
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public void delBigHash(String bigHashKey) {
        ScanOptions.ScanOptionsBuilder scanOptionsBuilder = ScanOptions.scanOptions();
        scanOptionsBuilder.count(100);
        scanOptionsBuilder.match("users");
        ScanOptions build = scanOptionsBuilder.build();
        HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        stringObjectObjectHashOperations.getOperations();
    }

    @Override
    public void delBigList(String bigListKey) {

    }

    @Override
    public void delBigSet(String bigSetKey) {

    }

    @Override
    public void delBigZSet(String bigZSetKey) {

    }
}
