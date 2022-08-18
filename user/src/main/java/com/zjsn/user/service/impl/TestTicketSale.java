package com.zjsn.user.service.impl;

import cn.hutool.core.date.DateUtil;
import com.zjsn.domain.sale.Ticket;
import com.zjsn.user.Mapper.TicketSaleMapper;
import com.zjsn.user.demo.juc.TicketSingletonTest;
import com.zjsn.user.service.TicketSaleService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

@Service
public class TestTicketSale implements TicketSaleService {

    @Autowired
    TicketSaleMapper ticketSaleMapper;
    @Resource
    private RedisTemplate redisTemplate;
    private volatile boolean flag = true;

    private List<Ticket> ticketList = new CopyOnWriteArrayList<>();

    private final String GoodsId = "111";
    private final String TICKET_PREFIX = "TestTicket::GoodsId::";

    @Override
    public String sale(Integer tickets) throws InterruptedException {
        // 获取票数
        CountDownLatch ticketNum = getTicketNum(tickets);
        testThread("aa", ticketNum);

        testThread("bb", ticketNum);

        testThread("cc", ticketNum);

        testThread("dd", ticketNum);

        testThread("ff", ticketNum);

        if (flag) {
            return "售卖成功";
        }
        return "售票完了";
    }

    @Override
    public String reseatTicket() {
        TicketSingletonTest.reseat();
        flag = true;
        return "票仓重制成功";
    }

    /**
     * 获取票数
     *
     * @return
     */
    private CountDownLatch getTicketNum(Integer tickets) {
        CountDownLatch countDownLatch = TicketSingletonTest.checkHasTicketCount();
        if (Objects.isNull(countDownLatch)) {
            // 获取数据需要时间
            this.timeConsumingOperation();
            int ticketNum = 30;
            if (!Objects.isNull(tickets)) {
                ticketNum = tickets;
            }
            return TicketSingletonTest.getTicketSingletonTest(ticketNum);
        } else {
            return countDownLatch;
        }
    }

    private  void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testThread(String threadName, CountDownLatch ticketNum) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                if (flag) {
                    for (int i = 0; i < 7; i++) {
                        try {
                            Ticket sale = ticketSaleMapper.sale(ticketNum);
                            if (!Objects.isNull(sale)) {
                                ticketList.add(sale);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    ticketNum.await();
                    if (flag) {
                        flag = false;
                        saveInRedis(ticketList);
                    }
                } else {
                    System.out.println("已经售卖完了");
                }
            }
        }, threadName).start();
    }

    private synchronized void saveInRedis(List<Ticket> ticketList) {
        if (!CollectionUtils.isEmpty(ticketList)) {
            System.out.println(ticketList);
            String nowDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
            String prefixKey =  TICKET_PREFIX + GoodsId + "::" + nowDateStr;
            for (Ticket ticket : ticketList) {
                try {
                    redisTemplate.opsForSet().add(prefixKey, ticket);
                } catch (Exception e) {

                }
            }
            System.out.println("添加成功");
        }
    }
}
