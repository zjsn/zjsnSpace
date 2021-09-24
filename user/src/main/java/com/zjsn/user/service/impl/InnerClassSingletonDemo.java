package com.zjsn.user.service.impl;

public class InnerClassSingletonDemo {

    private InnerClassSingletonDemo() {
        System.out.println("我是静态内部类私有化构造器");
    }

    public static class singletonClassInstance {
        private static final InnerClassSingletonDemo innerClassSingletonDemo = new InnerClassSingletonDemo();
    }

    public static InnerClassSingletonDemo getInstance() {
        return singletonClassInstance.innerClassSingletonDemo;
    }

    public static void main(String[] args) {
        InnerClassSingletonDemo instance = InnerClassSingletonDemo.getInstance();
        InnerClassSingletonDemo instance1 = InnerClassSingletonDemo.getInstance();
        System.out.println(instance == instance1);
        System.out.println(instance.equals(instance1));
    }
}
