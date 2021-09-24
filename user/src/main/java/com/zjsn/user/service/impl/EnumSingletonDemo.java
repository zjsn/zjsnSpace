package com.zjsn.user.service.impl;

public class EnumSingletonDemo {

    private EnumSingletonDemo() {
        System.out.println("我是枚举单例方法的私有化构造器");
    }

    public static EnumSingletonDemo getInstance() {
        return SingletonEnum.INSTANCE.getSingletonDemo();
    }

    private enum SingletonEnum {
        INSTANCE;

        // 枚举元素为单例
        private EnumSingletonDemo singletonDemo;

        private SingletonEnum () {
            System.out.println("枚举Demo私有构造参数");
            singletonDemo = new EnumSingletonDemo();
        }

        public EnumSingletonDemo getSingletonDemo() {
            return singletonDemo;
        }
    }

    public static void main(String[] args) {
        System.out.println(111111);
        EnumSingletonDemo singletonDemo = EnumSingletonDemo.getInstance();
        EnumSingletonDemo singletonDemo1 = EnumSingletonDemo.getInstance();
        System.out.println(singletonDemo == singletonDemo1);
        System.out.println(singletonDemo.equals(singletonDemo1));
    }
}
