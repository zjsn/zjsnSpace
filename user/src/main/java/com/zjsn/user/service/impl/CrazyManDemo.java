package com.zjsn.user.service.impl;

public class CrazyManDemo {

    private static CrazyManDemo crazyManDemo = new CrazyManDemo();

    private CrazyManDemo() {
        System.out.println("我是饿汉式,私有化构造器");
    }

    public static CrazyManDemo getInstance() {
        return crazyManDemo;
    }

    public static void main(String[] args) {
        CrazyManDemo instance = CrazyManDemo.getInstance();
        CrazyManDemo instance1 = CrazyManDemo.getInstance();
        System.out.println(instance == instance1);
        System.out.println(instance.equals(instance1));
    }
}
