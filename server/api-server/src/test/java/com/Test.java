package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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



        Dto d1 = new Dto(1L, "zsw");
        Dto d2 = new Dto(2L, "asda");
        Dto d3 = new Dto(3L, "afs");
        Dto d4 = new Dto(4L, "rqw");
        Dto d5 = new Dto(5L, "nyg");

        List<Dto> list = new ArrayList<Dto>(){{
            add(d1);
            add(d2);
            add(d3);
            add(d4);
            add(d5);
        }};

        Map<Long, List<Dto>> map = list.stream().collect(Collectors.groupingBy(Dto::getId));
        map.forEach((k, v) ->{
            System.out.println(k);
            System.err.println(v);
        });


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

    static class Dto {
        private Long id;
        private String name;

        public Dto(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        /**  */
        public Long getId() {
            return id;
        }

        /**  */
        public void setId(Long id) {
            this.id = id;
        }

        /**  */
        public String getName() {
            return name;
        }

        /**  */
        public void setName(String name) {
            this.name = name;
        }
    }


}
