package com.zjsn.user.demo.juc;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        // 线程池
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("执行处理操作");
            timeConsumingOperation();
            return 100;
        });
        System.out.println("计算结果:" + future.get());
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l)+ "ms");
    }

    private static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
