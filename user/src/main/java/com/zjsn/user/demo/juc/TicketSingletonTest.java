package com.zjsn.user.demo.juc;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

public class TicketSingletonTest implements Serializable, Cloneable{
    private static volatile CountDownLatch ticketCountDownLatch = null;

    public static CountDownLatch getTicketSingletonTest(int num) {
        if (null == ticketCountDownLatch) {
            synchronized(TicketSingletonTest.class) {
                if (null == ticketCountDownLatch) {
                    ticketCountDownLatch = new CountDownLatch(num);
                }
            }
        }
        return ticketCountDownLatch;
    }

    public static CountDownLatch checkHasTicketCount() {
        return ticketCountDownLatch;
    }

    public static synchronized void reseat() {
        ticketCountDownLatch = null;
    }


    /* 如果该对象被⽤于序列化，可以保证对象在序列化前后保持⼀致 */
    public Object readResolve() {
        return ticketCountDownLatch;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
