package com.zjsn.person.feign.feignHelper;

import com.zjsn.person.feign.RegistryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class RegistryHelper {
    @Autowired
    private RegistryClient registryClient;


}
