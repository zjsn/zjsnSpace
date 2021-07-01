package com.zjsn.user.feign.feignHelper;

import com.zjsn.user.feign.RegistryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: tangjiaren
 * @Date: 2021/5/26 16:20
 */
@Slf4j
@Component
public class RegistryHelper {
    @Autowired
    private RegistryClient registryClient;


    public String visitRegistry(String name) {
        return registryClient.visitRegistry(name);
    }
}
