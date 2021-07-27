package com.zjsn.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zjsn.domain.sale.Ticket;
import com.zjsn.user.Mapper.TicketSaleMapper;
import com.zjsn.user.service.TicketSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestTicketSale implements TicketSaleService {

    @Autowired
    TicketSaleMapper ticketSaleMapper;
    @Override
    public void sale() {
        List<Ticket> ticketList = new ArrayList<>();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                Ticket ticket = ticketSaleMapper.sale();
                if (ObjectUtil.isNotNull(ticket)) {
                    ticketList.add(ticket);
                }
            }
        },"aa").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                Ticket ticket = ticketSaleMapper.sale();
                if (ObjectUtil.isNotNull(ticket)) {
                    ticketList.add(ticket);
                }
            }
        },"bb").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                Ticket ticket = ticketSaleMapper.sale();
                if (ObjectUtil.isNotNull(ticket)) {
                    ticketList.add(ticket);
                }
            }
            System.out.println(ticketList);
        },"cc").start();
    }
}
