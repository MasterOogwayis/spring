package com.zsw.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

/**
 * @author ZhangShaowei on 2020/4/15 13:47
 */
public class TestJavassist {

    private static final ClassPool CLASS_POOL = ClassPool.getDefault();

    public static void main(String[] args) throws Exception {
        CtClass clazz = CLASS_POOL.get("com.zsw.javassist.Waiter");
        CtMethod get = clazz.getDeclaredMethod("get");
        get.insertBefore("{System.err.println($1);}");
        clazz.writeFile();

        Waiter waiter = (Waiter) clazz.toClass().newInstance();
        waiter.get("123");


    }


}
