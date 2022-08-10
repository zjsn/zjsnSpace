package com.zjsn.user.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


@Service
public class IRedisDelBigKeysImpl implements IRedisDelBigKeys{
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public void delBigHash(String bigHashKey) {
        try {
            Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan(bigHashKey, ScanOptions.scanOptions().match("*").count(100).build());
            while (scan.hasNext()) {
                // 删除Hash 里面的HashKey
                redisTemplate.opsForHash().delete(bigHashKey, scan.next().getKey());
            }
            // 使用scan操作完的时候,底层是不会释放连接.会导致连接池被用完.需要用户手动关闭
            scan.close();
            //最终删除key
            redisTemplate.delete(bigHashKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delBigList(String bigListKey) {
        Long size = redisTemplate.opsForList().size(bigListKey);
        if (!Objects.isNull(size)) {
            int counter = 0;
            int left = 100;
            while (counter < size) {
                //每次从左侧截掉100个
                redisTemplate.opsForList().trim(bigListKey, left, size);
                counter += left;
            }
        }
        //最终删除key
        redisTemplate.delete(bigListKey);
    }

    @Override
    public void delBigSet(String bigSetKey) {
        try {
            Cursor<String> scan = redisTemplate.opsForSet().scan(bigSetKey, ScanOptions.scanOptions().match("*").count(100).build());
            while (scan.hasNext()) {
                redisTemplate.opsForSet().remove(bigSetKey, scan.next());
            }
            // 使用scan操作完的时候,底层是不会释放连接.会导致连接池被用完.需要用户手动关闭
            scan.close();
            //最终删除key
            redisTemplate.delete(bigSetKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delBigZSet(String bigZSetKey) {
        try {
            Cursor<ZSetOperations.TypedTuple<String>> scan = redisTemplate.opsForZSet().scan(bigZSetKey, ScanOptions.scanOptions().match("*").count(100).build());
            while (scan.hasNext()) {
                redisTemplate.opsForZSet().remove(bigZSetKey, scan.next().getValue());
            }
            // 使用scan操作完的时候,底层是不会释放连接.会导致连接池被用完.需要用户手动关闭
            scan.close();
            //最终删除key
            redisTemplate.delete(bigZSetKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
