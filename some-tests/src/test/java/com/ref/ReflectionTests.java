package com.ref;

import lombok.SneakyThrows;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author Shaowei Zhang on 2022/3/8 11:15
 */
public class ReflectionTests {


    @SneakyThrows
    public static void main(String[] args) throws ClassNotFoundException {
//        MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(A.class, MethodHandles.lookup());
        MethodHandles.Lookup lookup = MethodHandles.lookup().in(A.class);
        MethodHandle getName = lookup.findSpecial(A.class, "getName", MethodType.methodType(String.class), A.class);
        System.out.println(getName.invoke(new C()));
        System.err.println(getName.invoke(new B()));

        MethodHandles.Lookup l = MethodHandles.lookup().in(A.class);
        MethodHandle g = l.findSpecial(A.class, "getName", MethodType.methodType(String.class), A.class);
        System.out.println(g.bindTo(new C()).invoke());


    }


    public static class A {

        public String getName() {
            return A.class.getName();
        }

    }

    public static class B extends A {
        @Override
        public String getName() {
            return B.class.getName();
        }
    }

    public static class C extends B {
        @Override
        public String getName() {
            return C.class.getName();
        }
    }

}
