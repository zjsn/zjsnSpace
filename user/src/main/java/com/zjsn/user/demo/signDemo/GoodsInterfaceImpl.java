package com.zjsn.user.demo.signDemo;

import org.springframework.stereotype.Service;
@Service
public class GoodsInterfaceImpl implements GoodsInterface{

    @Override
    public void queryGoodsMethod1() {
        System.out.println("111");
    }

    @Override
    public void queryGoodsMethod2() {
        System.out.println("222");
    }
}
