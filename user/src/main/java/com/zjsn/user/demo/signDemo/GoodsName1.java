package com.zjsn.user.demo.signDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoodsName1 extends AbstractGoodsClass{
    @Override
    public void queryGoodsMethod1() {
        super.queryGoodsMethod1();
        log.info("我是商品名字1的查询");
    }
}
