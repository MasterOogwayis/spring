package com.zsw.test;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author ZhangShaowei on 2020/5/26 14:14
 */
public class MethodHandlsTests {

    private static final int ALLOWED_MODES = MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
            | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC;

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(2 << 12);
        System.out.println((2 << 12) - 1);

        System.err.println(((2 << 12) -1) & 8195);


    }

    public static void testHandlerProxy() {
        DoSomething waiter = new Waiter();
        DoSomething instance = new JdkProxy().getInstance(waiter);
        instance.work();
    }

    public static void test2() throws Throwable {
        B b = new B();
        MethodHandles.Lookup lookup = BeanUtils.instantiateClass(
                MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class), A.class, ALLOWED_MODES);
//        MethodHandles.Lookup lookup = MethodHandles.lookup();`

        MethodHandle t = lookup.findSpecial(A.class, "t", MethodType.methodType(void.class), A.class);

        t.bindTo(b).invoke();
    }


    public static void test1() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class);

        Dto dto = new Dto();
        dto.setName("zsw");
        MethodHandle getName = lookup.findVirtual(Dto.class, "getName", methodType);
        Object o = getName.bindTo(dto).invoke();
        System.out.println(o);

        MethodHandle setName = lookup.findVirtual(Dto.class, "setName", MethodType.methodType(void.class, String.class));
        setName.bindTo(dto).invoke("Hello World");
        System.err.println(dto);

        MethodHandles.Lookup lookup1 = BeanUtils.instantiateClass(MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class), Dto.class, ALLOWED_MODES);
        MethodHandle set = lookup1.findSpecial(Dto.class, "set", MethodType.methodType(void.class, String.class), Dto.class);
        set.bindTo(dto).invoke("asdasdasd");


        System.out.println(dto);
    }

    interface DoSomething {
        default void work() {
            System.out.println("Do nothing ...");
            this.sayHello();
        }

        void sayHello();
    }

    static class Waiter implements DoSomething {

        @Override
        public void sayHello() {
            System.out.println("Hello World!");
        }

    }


    @Data
    static class Dto {

        private String name;

        public String sayHello(String name) {
            return "Hello " + name + "!";
        }

        private void set(String name) {
            this.name = name;
        }

    }

    static class A {
        public void t() {
            System.out.println("A");
        }
    }

    static class B extends A {
        @Override
        public void t() {
            System.out.println("B");
        }
    }


}
