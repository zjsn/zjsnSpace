package com.zjsn.user.demo.juc;

/*同步demo*/
public class SysDemo {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int i = syncCalculate();
        System.out.println("计算结果:" + i);
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l)+ "ms");
    }

    private static int syncCalculate() {
        System.out.println("执行处理操作");
        timeConsumingOperation();
        return 100;
    }

    private static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
