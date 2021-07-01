package com.zjsn.user.service;

import com.zjsn.domain.user.UserMongoEntity;
import org.springframework.stereotype.Service;

/**
 * @Author: tangjiaren
 * @Date: 2021/6/10 16:20
 */
@Service
public interface UserMongoService {

    UserMongoEntity findByIdContaining(Long id);

    UserMongoEntity save(UserMongoEntity userMongoEntity);
}
