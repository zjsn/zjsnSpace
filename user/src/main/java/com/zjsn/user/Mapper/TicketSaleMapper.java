package com.zjsn.user.Mapper;

import com.zjsn.domain.sale.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

@Repository
public class TicketSaleMapper {
    private final ReentrantLock lock = new ReentrantLock(); // 非公平锁 效率高 其他线程可能饿死
//    private final ReentrantLock lock = new ReentrantLock(true); // 公平锁 效率相对底 其他线程 雨露均沾
    // 售票
    public Ticket sale(CountDownLatch ticketNum) throws InterruptedException {
        lock.lock();
        try {
            if (ticketNum.getCount() > 0) {
                if ("dd".equals(Thread.currentThread().getName())) {
                    throw new InterruptedException();
                }
                Ticket ticket = new Ticket(Thread.currentThread().getId(), Thread.currentThread().getId());
                System.out.println("销售员" + Thread.currentThread().getName() + "剩余票子" + ticketNum.getCount());
                // 票数减一
                ticketNum.countDown();
                return ticket;
            } else {
                System.out.println("票子卖完啦");
            }
        } catch (InterruptedException e) {
            System.out.println("售票小姐姐已经下班了,卖票失败"+ e);
        } finally {
            lock.unlock();
        }
        return null;
    }

}
