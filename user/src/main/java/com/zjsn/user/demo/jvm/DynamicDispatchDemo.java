package com.zjsn.user.demo.jvm;

public class DynamicDispatchDemo {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println(" i am man");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("i am woman");
        }
    }

    public static void main(String[] args) {
        Human guy = new Man();
        Human guy1 = new Woman();
        guy.sayHello();
        guy1.sayHello();
        guy = new Woman();
        guy.sayHello();
    }
}
