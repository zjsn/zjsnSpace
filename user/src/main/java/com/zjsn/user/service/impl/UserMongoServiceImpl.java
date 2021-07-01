package com.zjsn.user.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zjsn.domain.user.UserMongoEntity;
import com.zjsn.user.service.UserMongoService;
import com.zjsn.user.service.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: tangjiaren
 * @Date: 2021/6/10 16:20
 */
@Service
@Slf4j
public class UserMongoServiceImpl implements UserMongoService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @SentinelResource(value = "usesRepository-findByIdContaining", blockHandler = "findByIdContainingExceptionHandle", fallback = "findByIdContainingFallback")
    public UserMongoEntity findByIdContaining(Long id) {
        return userRepository.findByIdContaining(id);
    }

    @Override
    @SentinelResource(value = "usesRepository-save", blockHandler = "saveExceptionHandle", fallback = "saveFallback")
    public UserMongoEntity save(UserMongoEntity userMongoEntity) {
        return userRepository.save(userMongoEntity);
    }

    public UserMongoEntity findByIdContainingFallback(Long id) {
        log.error("fallbackHandle:" + id);
        return new UserMongoEntity();
    }

    public UserMongoEntity findByIdContainingExceptionHandle(Long id, BlockException exception) {
        log.error("接口堵塞了." + id, exception);
        return new UserMongoEntity();
    }

    public UserMongoEntity saveFallback(UserMongoEntity userMongoEntity) {
        log.error("fallbackHandle:" + userMongoEntity);
        return new UserMongoEntity();
    }

    public UserMongoEntity saveExceptionHandle(UserMongoEntity userMongoEntity, BlockException exception) {
        log.error("接口堵塞了." + userMongoEntity, exception);
        return new UserMongoEntity();
    }
}
