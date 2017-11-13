package com;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zsw.base.utils.XmlUtils;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2017/9/18 10:03
 */
//@SpringBootTest(classes = Test.class)
public class Test {

    private static XStream xstream = new XStream(new XppDriver());


    /**
     *
     */
//    @org.junit.Test
    public void test() {

        System.err.println("123");

    }


    public static void main(String[] args) throws InterruptedException {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");


        List<String> newList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(newList);

        String c = list.stream().min(Comparator.comparing(s -> s.charAt(0))).get();
        System.err.println(c);


//        hello("This is zsw speaking! Who is that?", Test::callback);


//        System.out.println("1‬".length()); 不可见字符
//        System.out.println("1".length());

//        System.err.println((int)("1‬".charAt(1)));
//
//        char hide = (char) 8236;
//        String str = "1" + String.valueOf(hide);
//        System.out.println(str);
//        System.out.println(str.length());


    }


    public static void hello(String message, Consumer<String> callback) {
        callback.accept(message);
    }


    public static void callback(String str) {
        System.out.println(str);
    }

    abstract static class Dto {


        abstract void fun(String string);




    }

    static class Person {

        private Integer id;

        private String name;

        private String address;

        private Integer age;

        private List<String> roles;

        /**  */
        public Integer getId() {
            return id;
        }

        /**  */
        public void setId(Integer id) {
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

        /**  */
        public String getAddress() {
            return address;
        }

        /**  */
        public void setAddress(String address) {
            this.address = address;
        }

        /**  */
        public Integer getAge() {
            return age;
        }

        /**  */
        public void setAge(Integer age) {
            this.age = age;
        }

        public Person(Integer id, String name, String address, Integer age) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.age = age;
        }

        /**  */
        public List<String> getRoles() {
            return roles;
        }

        /**  */
        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", age=" + age +
                    ", roles=" + roles +
                    '}';
        }
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
