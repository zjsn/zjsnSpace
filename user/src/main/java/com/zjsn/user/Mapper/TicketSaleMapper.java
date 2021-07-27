package com.zjsn.user.Mapper;

import com.zjsn.domain.sale.Ticket;
import org.springframework.stereotype.Repository;

import java.util.concurrent.locks.ReentrantLock;

@Repository
public class TicketSaleMapper {
    int num = 30;
    private final ReentrantLock lock = new ReentrantLock();

    public Ticket sale() {
        lock.lock();
        try {
            if (num > 0) {
                Ticket ticket = new Ticket(Thread.currentThread().getId(),Thread.currentThread().getId());
                System.out.println("销售员"+Thread.currentThread().getName()+"剩余票子"+num--);
                return ticket;
            }
        } finally {
            lock.unlock();
        }
        return null;
    }
}
