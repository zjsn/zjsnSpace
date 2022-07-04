package com.zjsn.user.demo.jvm;

public class SubClass extends SuperClassDemo{
    static {
        System.out.println("我是子集,初始化开始.....");
    }
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                FieldResolution fieldResolution = new FieldResolution();
                System.out.println(Thread.currentThread() + "stop");
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread.start();
        thread2.start();
    }
}
