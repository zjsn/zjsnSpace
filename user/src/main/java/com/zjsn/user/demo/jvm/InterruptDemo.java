package com.zjsn.user.demo.jvm;

public class InterruptDemo {
    public static void main(String[] args) {
        demo2();
    }

    public static void demo1() {
        Thread thread = new Thread() {
            public void run() {
                System.out.println("线程启动了");
                try {
                    System.out.println("我去做业务查询,需要话费大量时间");
                    this.join(100L * 1000);
                } catch (InterruptedException e) {
                    System.out.println("查询时间超时,舍弃处理");
                }
                System.out.println("线程结束了");
            }
        };
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        try {
            Thread.sleep(5L * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();// 在线程阻塞时抛出一个中断信号，这样线程就得以退出阻塞的状态
    }

    public static void demo2() {
        Thread thread = new Thread() {
            public void run() {
                System.out.println("线程启动了");
                while (!isInterrupted()) {
                    System.out.println(isInterrupted());
                }
                System.out.println("线程结束了");
            }
        };
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("线程是否被中断：" + thread.isInterrupted());//true
    }
}
