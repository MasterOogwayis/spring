package com.zsw;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author ZhangShaowei on 2021/9/23 13:50
 */
public class StaticTests {

    public static void main(String[] args) {
//        TestThisService service = new JdkProxy().getInstance(new TestThisService());
//        System.out.println(AnnotationUtils.isCandidateClass(service.getClass(), Wrapped.class));


        char c = '☑';
        System.out.println(c);
        int i = c;
        System.err.println(i);

        System.out.println((int)'哈');

    }

}
