package com.zsw.orm.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangshaowei on 2023/7/21 10:31
 */
public class FastCopyTests {

    public static void main(String[] args) {
        Source source = new Source(1L, "Hello");
        Target target = new Target();
        target.setId(2L);
        FastCopy.copyNotNullProperties(source, target);

        System.out.println(target);
    }




    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Source {

        private Long id;

        private String name;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Target {
        private Long id;

        private String name;
    }

}
