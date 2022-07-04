package com.zjsn.user.demo.signDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractGoodsClass implements GoodsInterface {
    public void queryGoodsMethod1() {
        log.info("我是方法1");
    }

    public void queryGoodsMethod2() {
        log.info("我是方法2");
    }
}
