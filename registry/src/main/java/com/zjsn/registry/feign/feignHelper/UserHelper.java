package com.zjsn.registry.feign.feignHelper;

import com.zjsn.registry.feign.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: tangjiaren
 * @Date: 2021/5/26 17:00
 */
@Slf4j
@Component
public class UserHelper {

    @Autowired
    private UserClient userClient;

    public String visitUserCenter(String names) {
        String s = userClient.visitUserCenter(names);
        return s;
    }
}
