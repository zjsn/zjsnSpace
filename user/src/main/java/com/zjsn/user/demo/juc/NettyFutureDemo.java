package com.zjsn.user.demo.juc;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

import java.util.concurrent.CountDownLatch;

public class NettyFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        DefaultEventExecutorGroup eventExecutors = new DefaultEventExecutorGroup(4);
        Future<Integer> nettyFuture = eventExecutors.submit(() -> {
            System.out.println("执行处理操作");
            timeConsumingOperation();
            return 100;
        });
        // 回调式实现一 –Netty
        nettyFuture.addListener((FutureListener<Object>) future -> System.out.println("计算结果:：" + future.get()));
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l)+ "ms");
        new CountDownLatch(1).await();
    }

    private static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
