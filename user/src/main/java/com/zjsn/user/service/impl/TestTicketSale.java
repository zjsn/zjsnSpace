package com.zjsn.user.service.impl;

import com.zjsn.user.Mapper.TicketSaleMapper;
import com.zjsn.user.service.TicketSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestTicketSale implements TicketSaleService {

    @Autowired
    TicketSaleMapper ticketSaleMapper;
    @Override
    public void sale() {
        testThread("aa");

        testThread("bb");

        testThread("cc");
    }

    private void testThread(String threadName) {
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    ticketSaleMapper.sale();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, threadName).start();
    }
}
