package com.zjsn.user.service.impl;

public class LazyManDemo {
    private static LazyManDemo lazyManDemo;

    private LazyManDemo() {
        System.out.println("我是懒汉式的私有化构造器");
    }

    public synchronized static LazyManDemo getInstance() {
        boolean flag = true;
        if (lazyManDemo == null) {
            System.out.println("当前对象为空,需要重新new一个出来");
            flag = false;
            lazyManDemo = new LazyManDemo();
        }
        if (flag) {
            System.out.println("当前对象不为空");
        }
        return lazyManDemo;
    }

    public static void main(String[] args) {
        LazyManDemo instance = LazyManDemo.getInstance();
        LazyManDemo instance1 = LazyManDemo.getInstance();
        System.out.println(instance == instance1);
        System.out.println(instance.equals(instance1));
    }
}
