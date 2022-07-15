package com.zjsn.user.demo.juc;

/**
 * volatile变量自增运算测试
 *
 */
public class VolatileTest {
    public static volatile int race = 0;
    static volatile boolean shutDownRequested ;

    public static void increase() {
        // 会自动提示非原子操作 会出现线程不安全
        race++;
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] thread = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            thread[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            });
            thread[i].start();
        }
        // 等待所有累加线程都结束
        while(Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(race);
//        while(!shutDownRequested) {
//            System.out.println(1);
//        }
    }
}
