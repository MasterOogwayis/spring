package com.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

/**
 * @author zhangshaowei on 2023/7/7 11:21
 */
public class SingularTests {

    public static void main(String[] args) {
        S s = S.builder().category("a").build();
        System.out.println(s);
    }

    @Data
    @Builder
    private static class S {

        @Singular
        private List<String> categories;
    }

}
