package com.demo;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @author Shaowei Zhang on 2019/3/14 22:16
 **/
public class TestJvm {

    public static void main(String[] args) {


    }

    public static <T> T test1(List<T> list) {
        return list.get(0);
    }


    public class GrandFather {
        protected void thinking() {
            System.out.println(" I am grandfather");
        }
    }

    public class Father extends GrandFather {
        protected void thinking() {
            System.out.println(" I am father");
        }
    }

    public class Son extends Father {
        protected void thinking() {
            try {
                // 调用祖父的方法
                MethodType methodType = MethodType.methodType(Void.class);
                MethodHandle thinking = lookup().findSpecial(GrandFather.class, "thinking", methodType, getClass());
                thinking.invoke();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
