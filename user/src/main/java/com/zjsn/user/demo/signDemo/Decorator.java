package com.zjsn.user.demo.signDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Decorator implements GoodsInterface{
    private GoodsInterfaceImpl goodsInterface;

    public Decorator(GoodsInterfaceImpl goodsInterface) {
        super();
        this.goodsInterface = goodsInterface;
    }

    @Override
    public void queryGoodsMethod1() {
        log.info("sssss");
        goodsInterface.queryGoodsMethod1();
        log.info("bbbbb");
    }

    @Override
    public void queryGoodsMethod2() {
        log.info("before");
        goodsInterface.queryGoodsMethod2();
        log.info("after");
    }
}
