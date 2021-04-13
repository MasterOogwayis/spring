package com.zsw.lesson.l8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * 显示类型匹配的栈信息 -XX:+ShowHiddenFrames
 * -Djava.lang.invoke.MethodHandle.DUMP_CLASS_FILES=true
 *
 * 获取方法句柄的几种方式
 *
 * @author ZhangShaowei on 2021/4/13 13:49
 */
public class MethodHandlesTests {

    public static void main(String[] args) throws Throwable {
//        test1();
        test2();
    }

    public static void test2() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle bar2 = lookup.findStatic(MethodHandlesTests.class, "bar2", MethodType.methodType(void.class, Object.class));
        bar2.invokeExact(new Object());
    }

    public static void test1() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // 方式一
        Method bar = MethodHandlesTests.class.getDeclaredMethod("bar", Object.class);
        MethodHandle barHandle1 = lookup.unreflect(bar);
        barHandle1.invokeWithArguments("Hello ");

        // 方式二
        MethodType methodType = MethodType.methodType(void.class, Object.class);
        MethodHandle barHandle2 = lookup.findStatic(MethodHandlesTests.class, "bar", methodType);
        barHandle2.invokeWithArguments("World!");

        //        Object obj = "先将字符串定义成 Object 则不会报错";
//        barHandle2.invokeExact("这里会报错，因为是严格匹配参数类型.");
        barHandle2.invoke("test invoke");

        // 非静态方法需要绑定到实际对象上执行
        MethodHandle pri = lookup.findVirtual(MethodHandlesTests.class, "pri", MethodType.methodType(void.class, Object.class));
        pri.bindTo(new MethodHandlesTests()).invokeWithArguments("This is a test.");
    }


    private static void bar(Object object) {
        System.out.println("bar: " + object);
        new Exception().printStackTrace();
    }

    private static void bar2(Object object) {
        new Exception().printStackTrace();
    }

    public void pri(Object obj) {
        System.out.println(obj);
    }

}
