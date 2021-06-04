package com.demo.reflect;

import lombok.SneakyThrows;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZhangShaowei on 2021/6/4 9:45
 */
public class InterfaceTests {

    @SneakyThrows
    public static void main(String[] args) {
        int i = ThreadLocalRandom.current().nextInt(100);
        Named named = (i & 1) == 0 ? new A() : new B();
        System.out.println(named.getClass());

        NamedService namedService = new NamedService();
        Method p = NamedService.class.getDeclaredMethod("p", named.getClass());

        ReflectionUtils.makeAccessible(p);
        ReflectionUtils.invokeMethod(p, namedService, named);

    }

    static class NamedService {
        void p(A a) {
            System.out.println("a");
        }

        void p(B b) {
            System.out.println("b");
        }
    }


    interface Named {

    }


    static class A implements Named {

    }

    static class B implements Named {

    }

}
