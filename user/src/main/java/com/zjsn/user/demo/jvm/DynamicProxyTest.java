package com.zjsn.user.demo.jvm;


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

    public static void main(String[] args) {
        IHello bind = (IHello)new DynamicHello().bind(new sayHelloFun());
        bind.sayHello("zjsn", 18);
    }
}
