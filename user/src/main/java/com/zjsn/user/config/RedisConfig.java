package com.zjsn.user.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;
import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisConnectionFactory factory;

    /**
     * 缓存管理器
     *
     * @Cacheable(value = "user", key = "#root.targetClass.getCanonicalName()", unless = "#result eq null")
     * @CachePut
     * @CacheEvict
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        //设置缓存过期时间
        config.entryTtl(Duration.ofMillis(600));
        config.usePrefix();
        config.prefixKeysWith("cache:");
        return RedisCacheManager.builder(factory).cacheDefaults(config).build();
    }

    /**
     * 缓存注解的RedisTemplate
     * Jackson2JsonRedisSerializer来反序列化value值
     * StringRedisSerializer来序列化的key值
     */
    @Bean("cacheRedisTemplate")
    public RedisTemplate<Object, Object> cacheRedisTemplate() {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        serializer.setObjectMapper(om);

        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        //使用StringRedisSerializer来序列化的key值
        template.setKeySerializer(new StringRedisSerializer());

        template.afterPropertiesSet();

        return template;
    }

    /**
     * StringRedisSerializer来序列化和反序列化的RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //使用Jackson2JsonRedisSerializer来反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        serializer.setObjectMapper(om);

        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.setValueSerializer(serializer);

        redisTemplate.setConnectionFactory(factory);

        return redisTemplate;
    }

}
