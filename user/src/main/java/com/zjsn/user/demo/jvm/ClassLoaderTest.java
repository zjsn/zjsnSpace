package com.zjsn.user.demo.jvm;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream resourceAsStream = getClass().getResourceAsStream(fileName);
                    if (resourceAsStream == null ) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[resourceAsStream.available()];
                    resourceAsStream.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            };
        };
        Object o = classLoader.loadClass("com.zjsn.user.demo.jvm.ClassLoaderTest").newInstance();
        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
        System.out.println("用自己的类加载器去加载的对象在实例化");
        System.out.println(o instanceof ClassLoaderTest);
        System.out.println("直接new出来的对象");
        System.out.println(classLoaderTest instanceof ClassLoaderTest);
    }
}
