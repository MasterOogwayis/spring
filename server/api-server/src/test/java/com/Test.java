package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2017/9/18 10:03
 */
//@SpringBootTest(classes = Test.class)
public class Test {

//    private static XStream xstream = new XStream(new XppDriver());


    /**
     *
     */
//    @org.junit.Test
    public void test() {

        System.err.println("123");

    }


    public static void main(String[] args) throws Exception {


        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);

        System.out.println(map.compute("2", Integer::valueOf));
//        System.out.println(map.computeIfPresent("2", Integer::valueOf));

        System.err.println(map);
//        List<Person> list = new ArrayList<>();
//        list.add(new Person(1, "1", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(2, "2", "Moon", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(3, "3", "Mars", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(4, "4", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(5, "5", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(6, "6", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(7, "7", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(8, "8", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(9, "9", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(10, "10", "Earth", ThreadLocalRandom.current().nextInt(10)));
//
//        Map<Integer, List<String>> map = list.stream().collect(
//                Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList()))
//        );
//
//        System.out.println(map);
//
//
//        String names = list.stream().map(Person::getName).collect(Collectors.joining(","));
//        System.out.println(names);


//        hello("This is zsw speaking! Who is that?", Test::callback);


    }

    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<O>(), (acc, x) -> {
            // We are copying data from acc to new list instance. It is very inefficient,
            // but contract of Stream.reduce method requires that accumulator function does
            // not mutate its arguments.
            // Stream.collect method could be used to implement more efficient mutable reduction,
            // but this exercise asks to use reduce method.
            List<O> newAcc = new ArrayList<>(acc);
            newAcc.add(mapper.apply(x));
            return newAcc;
        }, (List<O> left, List<O> right) -> {
            // We are copying left to new list to avoid mutating it.
            List<O> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return newLeft;
        });
    }


    private static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, Integer::sum);
    }

    /**
     * 线程屏障
     */
    private static void cyclic() throws Exception {

        long timer = System.currentTimeMillis();

        AtomicLong counter = new AtomicLong();

        CyclicBarrier start = new CyclicBarrier(5001);
        CyclicBarrier after = new CyclicBarrier(5001);

        for (int i = 0; i < 5000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    start.await();
                    for (int j = 0; j < 100; j++) {
                        counter.incrementAndGet();
                    }
                    after.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
        start.await();
        after.await();
        System.out.println(counter.get());
        System.err.println(System.currentTimeMillis() - timer);
    }


    private static void unSee() {
        System.out.println("1‬".length()); //不可见字符
        System.out.println("1".length());

        System.err.println((int) ("1‬".charAt(1)));

        char hide = (char) 8236;
        String str = "1" + String.valueOf(hide);
        System.out.println(str);
        System.out.println(str.length());
    }

    private static boolean t() {
        System.out.println(1);
        return true;
    }

    static Integer sum(Stream<Integer> stream) {
        return stream.mapToInt(num -> num).sum();
    }


    public static void hello(String message, Consumer<String> callback) {
        callback.accept(message);
    }


    public static void callback(String str) {
        System.out.println(str);
    }

    abstract static interface Dto {


        void fun(String string);


    }


    static class Home {
        private Person person;

        private String address;

        /**  */
        public Person getPerson() {
            return person;
        }

        /**  */
        public void setPerson(Person person) {
            this.person = person;
        }

        /**  */
        public String getAddress() {
            return address;
        }

        /**  */
        public void setAddress(String address) {
            this.address = address;
        }
    }


}
