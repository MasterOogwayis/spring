package com;

import com.service.FirstService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import sun.reflect.misc.MethodUtil;

import java.lang.reflect.Method;
import java.util.List;
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


    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(-1L ^ (-1L << 5L));
        System.out.println(~(-1L << 5L));
        System.out.println(1L << 5L);
    }


    private static List<Method> getMethods(Class clazz, String methodName) {
        Method[] declaredMethods = clazz.getMethods();
        List<Method> mayMethods = Stream.of(declaredMethods)
                .filter(m -> methodName.equals(m.getName()))
                .collect(Collectors.toList());
        Assert.notEmpty(mayMethods, "未找到方法：" + methodName);
        return mayMethods;
    }


}
