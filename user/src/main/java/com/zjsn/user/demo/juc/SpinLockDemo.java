package com.zjsn.user.demo.juc;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    private AtomicReference<Thread> cas = new AtomicReference<Thread>();
    public void lock () {
        Thread thread = Thread.currentThread();
        // use cas
        while(!cas.compareAndSet(null, thread)) {
            System.out.println("自旋等待");
        }
    }
    public void unlock() {
        Thread thread = Thread.currentThread();
        cas.compareAndSet(thread, null);
    }
}
