package com.zjsn.user.service;

import org.springframework.stereotype.Service;

@Service
public interface IGoodsSecKillService {
    // 秒杀过程的方法
    boolean doSecKill(String uid, String prodId);

    void supplementGoodsCount(Integer num, String prodId);
}
