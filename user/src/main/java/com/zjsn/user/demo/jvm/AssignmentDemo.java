package com.zjsn.user.demo.jvm;

import java.util.Random;

public class AssignmentDemo {
    static abstract  class Human {

    }

    static class Man extends  Human {

    }

    static class Woman extends Human {

    }

    public void sayHello (Human human) {
        System.out.println("I am human");
    }

    public void sayHello (Man man) {
        System.out.println("I am man");
    }

    public  void sayHello (Woman woman) {
        System.out.println("I am woman");
    }

    /**
     * 我们把上面代码中的“Human”称为变量的“静态类型”（Static Type），或者叫“外观类 型”（Apparent Type），
     * 后面的“Man”则被称为变量的“实际类型”（Actual Type）或者叫“运行时类 型”（Runtime Type）。
     * 静态类型和实际类型在程序中都可能会发生变化，区别是静态类型的变化仅仅 在使用时发生，变量本身的静态类型不会被改变，
     * 并且最终的静态类型是在编译期可知的；而实际类 型变化的结果在运行期才可确定，编译器在编译程序的时候并不知道一个对象的实际类型是什么。
     *
     *
     *
     * main()里面的两次sayHello()方法调用，
     * 在方法接收者已经确定是对象“assignmentDemo”的前提下，
     * 使用哪个重载版本，就完全取决于传入参数的数量和数据类型。
     * 代码中故意定义了两个静态类型相同，而实际类型不同的变量，但虚拟机（或者准确地说是编译器）在重载时是通过参数的静态类型而不是实际类型作为判定依据的
     *
     * @param args
     */
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        AssignmentDemo assignmentDemo = new AssignmentDemo();
        assignmentDemo.sayHello(man);
        assignmentDemo.sayHello(woman);
        // 实际类型变化
        Human guy = (new Random()).nextBoolean() ? new Man() : new Woman();

        //静态类型变化
        assignmentDemo.sayHello(guy);
    }
}
