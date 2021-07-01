package com.zjsn.authority.controller.service;

import org.springframework.stereotype.Service;

/**
 * @Author: tangjiaren
 * @Date: 2021/6/1 11:15
 */
@Service
public interface AuthorityService {

    String sayHello(String name);

    String authorityOpen(String msg);

}
