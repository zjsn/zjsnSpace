package com.zjsn.user.demo.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

import static java.lang.invoke.MethodHandles.lookup;

public class Test {
    class GrandFather {
        void think () {
            System.out.println("i am grandFather");
        }
    }
    class Father extends GrandFather {
        void think () {
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        void think () {
            try {
                MethodType methodType = MethodType.methodType(void.class);
                MethodHandle think = lookup().findSpecial(GrandFather.class, "think", methodType, getClass());
                think.invoke(this);
            } catch (Exception e) {

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        void think2 () {
            try {
                MethodType methodType = MethodType.methodType(void.class);
                Field impl_lookup = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                impl_lookup.setAccessible(true);
                MethodHandle think = ((MethodHandles.Lookup) impl_lookup.get(null)).findSpecial(GrandFather.class, "think", methodType, getClass());
                think.invoke(this);
            } catch (Throwable a) {

            }
        }
    }

    public static void main(String[] args) {
        (new Test().new Son()).think();
        (new Test().new Son()).think2();
    }
}
