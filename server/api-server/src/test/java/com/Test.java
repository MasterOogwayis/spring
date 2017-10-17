package com;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author ZhangShaowei on 2017/9/18 10:03
 */
//@SpringBootTest(classes = Test.class)
public class Test {


    /**
     *
     */
//    @org.junit.Test
    public void test() {

        System.err.println("123");

    }


    public static void main(String[] args) {


//        System.out.println(Integer.class.getName());
//        System.out.println(Integer.class.getSimpleName());

//        String key = "name";
//
//        String str = Optional.ofNullable("1").orElse("2");
//
//        String data = Optional.ofNullable(getRequest()).map(request -> request.get(key)).orElse(
//                Optional.ofNullable(getSession()).map(session -> session.get(key)).orElse(
//                        Optional.ofNullable(getContext()).map(context -> context.get(key)).orElse(null)
//                )
//        );
//
//        System.out.println(data);

//        String[] values = {"1", "2 ", " 3", " 4 ", "", null};
//
//        List<Double> list =
//                Arrays.stream(values).filter(StringUtils::hasText)
//                        .map(Double::valueOf).collect(Collectors.toList());
//
//        System.out.println(list);

//        List<Integer> list = Stream.of(values).map(Integer::valueOf).collect(Collectors.toList());


//        List<Map<String, Object>> rows = new ArrayList<>();
//
//        double average = rows.stream().map(row -> row.get("")).map(String::valueOf).mapToDouble(Double::valueOf).average().orElse(0D);
//        System.err.println(average);
    }

    private static Map<String, String> getRequest() {
        return null;
    }

    private static Map<String, String> getSession() {
        return null;
    }

    private static Map<String, String> getContext() {
        return Collections.singletonMap("name", "zsw");
    }


    public static void hello(String message, Consumer<String> callback) {
        callback.accept(message);
    }


    public static void callback(String str) {
        System.out.println(str);
    }


}
