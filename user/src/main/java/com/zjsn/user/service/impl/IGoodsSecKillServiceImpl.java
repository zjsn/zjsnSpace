package com.zjsn.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zjsn.user.service.IGoodsSecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class IGoodsSecKillServiceImpl implements IGoodsSecKillService {
    @Autowired
    private RedisTemplate redisTemplate;
    // 秒杀商品key前缀
    private final String SEC_GOOD_KEY_PREFIX = "sec:goods:";
    // 秒杀成功用户前缀
    private final String SEC_SUCCESS_USER_PREFIX = "sec:success:user:";
    @Override
    public boolean doSecKill(String uid, String prodId) {
        // 如果uid、商品id为空的话就不执行了
        if (StrUtil.isNotBlank(uid) && StrUtil.isNotBlank(prodId)) {
            // 库存key
            String kcKey = SEC_GOOD_KEY_PREFIX + prodId;
            // 用户成功秒杀key
            String userKey = SEC_SUCCESS_USER_PREFIX + prodId;
            // redis监视一下这个key
            redisTemplate.watch(kcKey);
            // 返回商品库存
            Object kcCount = checkGoodsCount(kcKey);
            // 判断商品库存是否为空
            if (Objects.isNull(kcCount)) {
                log.info("秒杀活动还没开始呢");
                return false;
            }
            // 判断这个人是否重复参与秒杀活动
            if (checkUserRepeat(userKey, uid)) {
                log.info("当前用户{}已经参与过了",uid);
                return false;
            }
            if ((Integer)kcCount <= 0) {
                log.info("秒杀活动已经结束了");
                return false;
            }
            // redis开启事务
            redisTemplate.multi();
            // 库存减一
            redisTemplate.opsForValue().decrement(kcKey);
            // 秒杀成功的用户加1
            redisTemplate.opsForSet().add(userKey, uid);

            redisTemplate.exec();
        }
        return true;
    }

    @Override
    public void supplementGoodsCount(Integer num, String prodId) {
        if (!Objects.isNull(num) && StrUtil.isNotBlank(prodId)) {
            // 库存key
            String kcKey = SEC_GOOD_KEY_PREFIX + prodId;
            redisTemplate.opsForValue().set(kcKey, num, 1L, TimeUnit.DAYS);
        }
    }

    /**
     * 返回商品库存
     *
     * @param kcKey
     * @return
     */
    private Object checkGoodsCount(String kcKey) {
        return redisTemplate.opsForValue().get(kcKey);
    }

    /**
     * 判断用户是否重复秒杀这个
     *
     * @param userKey
     * @param uid
     * @return
     */
    private boolean checkUserRepeat(String userKey, String uid) {
        Boolean member = redisTemplate.opsForSet().isMember(userKey, uid);
        return !Objects.isNull(member) && member;
    }
}
