package com.demo;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ZhangShaowei on 2019/2/27 14:15
 **/
public class StaticTests {


    @SneakyThrows
    public static void main(String[] args) {
//        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

//        List<Integer> list = Arrays.asList(0x6a, 0x61, 0x76, 0x61, 0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f, 0x4f, 0x62, 0x6a, 0x65, 0x63, 0x74);
//        String str = list.stream().map(i -> {
//            int j = i;
//            char c = (char) j;
//            return String.valueOf(c);
//        }).collect(Collectors.joining());

//        String dateStr = "2019-03-06T11:15:08+08:00";
//
//
//        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);

        System.out.println(YearMonth.now().plusMonths(-1).atDay(1).format(DateTimeFormatter.ISO_DATE));

//        Dto dto = Dto.builder().build();
//        dto.add("String");
//        dto.isSuccess();

    }


    @Data
    @Builder
    static class Dto {

        @Accessors(fluent = true)
        private Boolean isSuccess;

        @Delegate
        private List<String> list;


    }


}
