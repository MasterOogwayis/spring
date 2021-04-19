//package com.zsw.lesson.l7;
//
///**
// * @author ZhangShaowei on 2021/4/13 10:45
// */
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//import jdk.internal.reflect.MethodAccessor;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.lang.reflect.UndeclaredThrowableException;
//
//public final class GeneratedMethodAccessor1 extends Proxy implements MethodAccessor {
//    private static Method m0;
//    private static Method m1;
//    private static Method m2;
//    private static Method m3;
//
//    public GeneratedMethodAccessor1(InvocationHandler var1) {
//        super(var1);
//    }
//
//    @Override
//    public final int hashCode() {
//        try {
//            return (Integer) super.h.invoke(this, m0, (Object[]) null);
//        } catch (RuntimeException | Error var2) {
//            throw var2;
//        } catch (Throwable var3) {
//            throw new UndeclaredThrowableException(var3);
//        }
//    }
//
//    @Override
//    public final boolean equals(Object var1) {
//        try {
//            return (Boolean) super.h.invoke(this, m1, new Object[]{var1});
//        } catch (RuntimeException | Error var3) {
//            throw var3;
//        } catch (Throwable var4) {
//            throw new UndeclaredThrowableException(var4);
//        }
//    }
//
//    @Override
//    public final String toString() {
//        try {
//            return (String) super.h.invoke(this, m2, (Object[]) null);
//        } catch (RuntimeException | Error var2) {
//            throw var2;
//        } catch (Throwable var3) {
//            throw new UndeclaredThrowableException(var3);
//        }
//    }
//
//    @Override
//    public final Object invoke(Object var1, Object[] var2) throws IllegalArgumentException, InvocationTargetException {
//        try {
//            return (Object) super.h.invoke(this, m3, new Object[]{var1, var2});
//        } catch (RuntimeException | InvocationTargetException | Error var4) {
//            throw var4;
//        } catch (Throwable var5) {
//            throw new UndeclaredThrowableException(var5);
//        }
//    }
//
//    static {
//        try {
//            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
//            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
//            m2 = Class.forName("java.lang.Object").getMethod("toString");
//            m3 = Class.forName("jdk.internal.reflect.MethodAccessor").getMethod("invoke", Class.forName("java.lang.Object"), Class.forName("[Ljava.lang.Object;"));
//        } catch (NoSuchMethodException var2) {
//            throw new NoSuchMethodError(var2.getMessage());
//        } catch (ClassNotFoundException var3) {
//            throw new NoClassDefFoundError(var3.getMessage());
//        }
//    }
//}
//
