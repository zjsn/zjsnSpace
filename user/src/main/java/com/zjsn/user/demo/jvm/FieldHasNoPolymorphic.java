package com.zjsn.user.demo.jvm;

/**
 *
 * 字段不参加多态
 *
 * 输出两句都是“I am Son”，这是因为Son类在创建的时候，
 * 首先隐式调用了Father的构造函数，
 * 而 Father构造函数中对showMeTheMoney()的调用是一次虚方法调用，实际执行的版本是 Son::showMeTheMoney()方法，
 * 所以输出的是“I am Son”，这点经过前面的分析相信读者是没有疑问的 了。
 * 而这时候虽然父类的money字段已经被初始化成2了，
 * 但Son::showMeTheMoney()方法中访问的却 是子类的money字段，这时候结果自然还是0，因为它要到子类的构造函数执行时才会被初始化。
 * main()的最后一句通过静态类型访问到了父类中的money，输出了2。
 *
 */
public class FieldHasNoPolymorphic {
    static class Father {
        public int money = 1;
        public Father() {
            money = 2;
            showMoney();
        }
        public void showMoney() {
            System.out.println("father has money $" + money);
        }
    }
    static class Son extends Father {
        public int money = 3;
        public Son() {
            money = 4;
            showMoney();
        }
        public void showMoney () {
            System.out.println("son has money $" + money);
        }
    }

    public static void main(String[] args) {
        Father father = new Son();
        System.out.println("this person has money $" + father.money);
    }
}
