package com.zjsn.user.service;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

@Api("门票售卖服务接口")
@Service
public interface TicketSaleService {

    void sale();
}
