package com.zjsn.user.demo.jvm;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DynamicProxyTest {
    /**
     * sayHello业务接口
     */
    interface IHello {
        void sayHello(String name, Integer age);
    }

    /**
     * 实现接口的具体方法
     */
    static class sayHelloFun implements IHello {
        @Override
        public void sayHello(String name, Integer age) {
            System.out.println("im "+ name +" desu,age is" + age);
        }
    }

    /**
     *  jdk动态代理处理参数
     */
    static class DynamicHello implements InvocationHandler {
        Object originalObj;

        Object bind (Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
                    originalObj.getClass().getInterfaces(),
                    this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object invoke = null;
            // 业务处理判断开始
            args = checkBusinessStartFun(args);
            invoke = method.invoke(originalObj, args);
            // 业务处理完成并打印
            checkBusinessEndFun(args);
            return invoke;
        }

        /**
         * 业务处理判断开始
         *
         * @param args
         * @return
         */
        private Object[] checkBusinessStartFun(Object[] args) {
            System.out.println("im zjsn-plus begin handle");
            System.out.println("接口原始参数"+Arrays.toString(args));
            List<Object> list = new LinkedList<>();
            for (Object o : args) {
                if (o instanceof String) {
                    String parameterName = (String) o;
                    o = parameterName + "Plus";
                }
                if (o instanceof Number) {
                    int parameterNum = (int) o;
                    o = parameterNum + 1;
                }
                list.add(o);
            }
            return list.toArray();
        }

        /**
         * 业务处理完成并打印
         *
         * @param args
         * @return
         */
        private Object[] checkBusinessEndFun(Object[] args) {
            System.out.println("im zjsn-plus end handle");
            System.out.println("代理处理完的参数"+Arrays.toString(args));
            return args;
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        IHello bind = (IHello)new DynamicHello().bind(new sayHelloFun());
        bind.sayHello("zjsn", 18);
//
//        Integer n1 = new Integer(47);
//        Integer n2 = new Integer(47);
//        System.out.println(n1 == n2);
//        System.out.println(n1 != n2);

//        byte a = 0;
//        byte b = 1;
//        byte c = 1;
//        byte f = 2;
//        byte d = 0;
//        System.out.println(b & c);
//        System.out.println(b | c);
//        System.out.println(a | d);
//        System.out.println(c ^ b);
//        System.out.println(a ^ d);
//        System.out.println(~ a);
//        System.out.println(~ f);


//        Integer a = 1;
//        Integer b = 2;
//        Integer c = 3;
//        Integer d = 3;
//        Integer e = 321;
//        Integer f = 321;
//        Long g = 3L;
//        System.out.println(c == d);
//        System.out.println(e == f);
//        System.out.println(c == (a + b));
//        System.out.println(c.equals(a + b));
//        System.out.println(g == (a + b));
//        System.out.println(g.equals(a + b));
//
//        String ss = new String("abc");
//        Field value = ss.getClass().getDeclaredField("value");
//        value.setAccessible(true);
//        value.set(ss,"abcd".toCharArray());
//        System.out.println(ss);

    }
}
