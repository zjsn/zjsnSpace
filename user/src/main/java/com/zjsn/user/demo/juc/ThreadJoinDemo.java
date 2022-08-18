package com.zjsn.user.demo.juc;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadJoinDemo {
    ReentrantLock reentrantLock = new ReentrantLock();
//    String
//    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
//    reentrantLock.
    public static void main(String[] args) throws InterruptedException {
        Thread testA = new ThreadTest("A");
        Thread testB = new ThreadTest("B");
        Thread testC = new ThreadTest("C");
        testB.start();
        testB.join();
        testA.start();
        testA.join();
        testC.start();
    }

    static class ThreadTest extends Thread {
        private final String name;

        public ThreadTest(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for(int i=1;i<=5;i++){
                System.out.println(name+"-"+i);
            }
        }
    }
}

