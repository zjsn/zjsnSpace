package com.zjsn.user.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zjsn.domain.user.UserMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

/**
 * @Author: tangjiaren
 * @Date: 2021/5/27 9:59
 */
@Service
public interface UserRepository extends MongoRepository<UserMongoEntity, Long> {

    UserMongoEntity findByIdContaining(Long id);

}
