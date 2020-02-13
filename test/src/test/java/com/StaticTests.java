package com;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2019/10/9 10:03
 **/
@Slf4j
//@RunWith(Parameterized.class)
public class StaticTests {

//    @Parameterized.Parameters
//    public static Object[][] data() {
//        return new Object[10][0]; // repeat count which you want
//    }


    @Test
    public void test() {

        System.out.println(System.currentTimeMillis());

        System.err.println(ZonedDateTime.now(ZoneId.systemDefault()).toInstant().toEpochMilli());

    }




}
