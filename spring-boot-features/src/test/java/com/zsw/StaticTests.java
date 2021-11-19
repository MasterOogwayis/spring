package com.zsw;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2021/9/23 13:50
 */
public class StaticTests {

    public static void main(String[] args) {
//        TestThisService service = new JdkProxy().getInstance(new TestThisService());
//        System.out.println(AnnotationUtils.isCandidateClass(service.getClass(), Wrapped.class));


        Map<String, List<String>> collect = Stream.of("1:1", "1:1", "2:2", "3:3")
                .distinct()
                .map(s -> s.split(":"))
                .collect(Collectors.groupingBy(a -> a[0], Collectors.mapping(a -> a[1], Collectors.toList())));

        System.out.println(collect);

    }

}
