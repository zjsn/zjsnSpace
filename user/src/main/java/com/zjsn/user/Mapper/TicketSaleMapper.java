package com.zjsn.user.Mapper;

import com.zjsn.domain.sale.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

@Repository
public class TicketSaleMapper {
    // 资源数
    private int num = 30;
    private List<Ticket> ticketList = new CopyOnWriteArrayList<>();
//    private final ReentrantLock lock = new ReentrantLock(); // 非公平锁 效率高 其他线程可能饿死
    private final ReentrantLock lock = new ReentrantLock(true); // 公平锁 效率相对底 其他线程 雨露均沾
    // 获取所有的票数
    CountDownLatch countDownLatch = new CountDownLatch(this.num);
    // 售票
    public void sale() throws InterruptedException {
        lock.lock();
        try {
            if (num > 0) {
                Ticket ticket = new Ticket(Thread.currentThread().getId(),Thread.currentThread().getId());
                System.out.println("销售员"+Thread.currentThread().getName()+"剩余票子"+--num);
                // 保存起来
                ticketList.add(ticket);
                // 票数减一
                countDownLatch.countDown();
                if (countDownLatch.getCount() == 0) {
                    // 票没卖完就会等待
                    System.out.println("最后一张票卖完,收工.卖票小姐姐叫" + Thread.currentThread().getName());
                    System.out.println("需要保存的售票记录数组为" + ticketList.toString());
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
