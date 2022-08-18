package com.zjsn.user.demo.jvm;

import com.zjsn.domain.user.BaseHeader;

public class TeatThreadLocal {
    private static final ThreadLocal<BaseHeader<Object>> baseLocal = new ThreadLocal<>();

    public static void set(BaseHeader<Object> baseHeader){
        baseLocal.set(baseHeader);
    }

    public static BaseHeader<Object> get(){
        return baseLocal.get();
    }

    public static void remove(){
        baseLocal.remove();
    }
}
