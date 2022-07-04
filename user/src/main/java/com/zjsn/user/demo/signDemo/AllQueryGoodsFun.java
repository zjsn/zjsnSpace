package com.zjsn.user.demo.signDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AllQueryGoodsFun {
    private GoodsName1 goodsName1;
    private GoodsName2 goodsName2;

    public AllQueryGoodsFun(GoodsName1 goodsName1, GoodsName2 goodsName2) {
        this.goodsName1 = goodsName1;
        this.goodsName2 = goodsName2;
    }
    public void startQuery() {
        log.info("开机");
        goodsName1.queryGoodsMethod1();
        goodsName2.queryGoodsMethod2();
        log.info("关机");
    }
}
