package com.zjsn.user.demo.juc;

import java.util.concurrent.CountDownLatch;
/*
   1、CountDownLatch end = new CountDownLatch(N); //构造对象时候 需要传入参数N

　　2、end.await()  能够阻塞线程 直到调用N次end.countDown() 方法才释放线程

　　3、end.countDown() 可以在多个线程中调用  计算调用次数是所有线程调用次数的总和
*/
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(10);
        for(int i=0; i<10; i++){
            Thread thread = new Thread(new Player(begin,end));
            thread.start();
        }

        try{
            System.out.println("the race begin");
            begin.countDown();
            end.await();
            System.out.println("the race end");
        }catch(Exception e){
            e.printStackTrace();
        }
    }






    static class Player implements Runnable {

        private CountDownLatch begin;
        private CountDownLatch end;

        public Player(CountDownLatch begin, CountDownLatch end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                begin.await();
                System.out.println(Thread.currentThread().getName() + " arrived !");
                end.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
