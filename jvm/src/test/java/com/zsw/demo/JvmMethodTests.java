package com.zsw.demo;

import com.zsw.utils.MethodHandlesUtils;
import lombok.SneakyThrows;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2021/9/7 13:50
 */
public class JvmMethodTests {

    public static void main(String[] args) {
        testInvoke();
    }

    @SneakyThrows
    public static void testInvoke() {
        Method t = Children.class.getDeclaredMethod("t");

        Children children = new Children();
        t.invoke(children);
    }


    @SneakyThrows
    public static void testInvokeStatic() {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle say = lookup.findStatic(Children.class, "hello", MethodType.methodType(void.class));
//        Children children = new Children();
        say.invoke();
    }

    /**
     * 调用父类被重写的方法
     * 输出 parent
     */
    @SneakyThrows
    public static void testInvokeVirtual() {
        MethodHandles.Lookup lookup = MethodHandlesUtils.lookup(Children.class);
        MethodHandle say = lookup.findSpecial(Parent.class, "say", MethodType.methodType(void.class), Children.class);
        say.bindTo(new Children()).invoke();
    }


    static class Children extends Parent {

        @Override
        public void say() {
            System.err.println("children ...");
        }

        public void t() {
            System.out.println("nothing ...");
        }
    }

    static class Parent implements Grad {

        public static void hello() {
            System.out.println("Hello World!");
        }

        @Override
        public void say() {
            System.out.println("parent ...");
        }

    }


    interface Grad {

        void say();

    }

}
