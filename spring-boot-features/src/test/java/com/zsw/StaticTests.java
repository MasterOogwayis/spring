package com.zsw;

/**
 * @author ZhangShaowei on 2021/9/23 13:50
 */
public class StaticTests {

    public static void main(String[] args) {
//        TestThisService service = new JdkProxy().getInstance(new TestThisService());
//        System.out.println(AnnotationUtils.isCandidateClass(service.getClass(), Wrapped.class));

        double a = (1d - (6 / 24d));
        double b = (1d - (6 / 23d));
        double c = (1d - (6 / 22d));

        System.out.println(1-(a * b * c));

    }

}
