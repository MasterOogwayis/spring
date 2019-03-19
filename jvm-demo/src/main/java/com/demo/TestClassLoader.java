package com.demo;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shaowei Zhang on 2019/3/12 22:48
 **/
public class TestClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Object obj = classLoader.loadClass("com.demo.TestClassLoader").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof TestClassLoader);

    }


}
