package com.zjsn.user.demo.jvm;

import java.io.Serializable;

public class SingletonTest implements Serializable, Cloneable {
    private static volatile SingletonTest singletonTest = null;
    public SingletonTest() {

    }
    public static SingletonTest getSingleton() {
        if (null == singletonTest) {
            synchronized (SingletonTest.class) {
                if (singletonTest == null ) {
                    singletonTest = new SingletonTest();
                }
            }
        }
        return singletonTest;
    }

    /* 如果该对象被⽤于序列化，可以保证对象在序列化前后保持⼀致 */
    public Object readResolve() {
        return singletonTest;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
