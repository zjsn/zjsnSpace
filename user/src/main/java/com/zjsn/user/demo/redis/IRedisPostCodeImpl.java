package com.zjsn.user.demo.redis;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class IRedisPostCodeImpl implements IRedisPostCode{

    @Autowired
    private StringRedisTemplate redisTemplate;
    private final String countKey = "VerifyCode:" ;
    // 手机验证码key
    private final String codeKey = "VerifyCode: " ;
    @Override
    public String createPostCode(String phone) {
        String code = getCode();
        // 手机号每天只能发送三次校验,加验证码存储到redis中并设置2分钟有效期
        boolean b = verifyCodeFun(phone, code);
        return b? code : "当前手机号已经超过次数限制了,请明天再试！";
    }

    @Override
    public Boolean verifyCode(String phone, String code) {
        return getRedisCode(phone, code);
    }

    private boolean getRedisCode(String phone, String code) {
        // 手机验证码key
        String verifyKey = codeKey + phone + ":code";
        // 手机发送次数key
        String sendKey = countKey + phone + ":count";
        String redisCode = ObjectUtil.isNotNull(redisTemplate.opsForValue().get(verifyKey))? redisTemplate.opsForValue().get(verifyKey): null;
        if (code.equals(redisCode)) {
            System.out.println("验证成功");
            // 删除对应的验证码
            redisTemplate.delete(Arrays.asList(verifyKey, sendKey));
            return true;
        } else {
            System.out.println("验证失败");
            return false;
        }
    }


    /**
     * 手机号每天只能发送三次校验,加验证码存储到redis中并设置2分钟有效期
     *
     * @param phone
     * @param vCode
     */
    private boolean verifyCodeFun(String phone, String vCode) {
        if (StrUtil.isNotBlank(phone) && StrUtil.isNotBlank(vCode)) {
            // 手机发送次数key
            String sendKey = countKey + phone + ":count";
            // 手机验证码key
            String verifyKey = codeKey + phone + ":code";
            // redis中获取这个key
            Integer countKey1 = ObjectUtil.isNotNull(redisTemplate.opsForValue().get(sendKey)) ? Integer.valueOf(redisTemplate.opsForValue().get(sendKey)) : null;
            // 如果为空的话代表没有发送过
            if (ObjectUtil.isNull(countKey1)) {
                redisTemplate.opsForValue().set(sendKey, "1", 1L, TimeUnit.DAYS);
            } else if (countKey1 <= 2) {
                // 发送次数加1
                redisTemplate.opsForValue().increment(sendKey);
            } else {
                // 发送次数超过3了
                return false;
            }
            // 存放验证码到redis中
            redisTemplate.opsForValue().set(verifyKey, vCode, 2L,TimeUnit.MINUTES);
        }
        return true;
    }

    /**
     * 生成验证码
     *
     * @return
     */
    public static String getCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i< 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }


}
