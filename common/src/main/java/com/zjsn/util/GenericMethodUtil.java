package com.zjsn.util;

import com.zjsn.domain.user.UserMongoEntity;

public class GenericMethodUtil {
    /**
     * 泛型方法
     *
     * @param printArray
     * @param <E>
     */
    public <E> void printArray(E[] printArray) {
        for (E e : printArray) {
            System.out.println(e.toString());
        }
    }

    /**
     * 盒子泛型类
     *
     * @param <T> 必须是 UserMongoEntity子类
     */
    public static class Box<T extends UserMongoEntity> {
        private  T t;
        public void add(T t) {
            this.t = t;
        }
        public T getT() {
            return this.t;
        }
    }
}
