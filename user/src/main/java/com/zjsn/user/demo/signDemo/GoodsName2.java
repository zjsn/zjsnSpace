package com.zjsn.user.demo.signDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoodsName2 extends AbstractGoodsClass{
    @Override
    public void queryGoodsMethod2() {
        super.queryGoodsMethod2();
        log.info("我是商品名字2的查询");
    }
}
