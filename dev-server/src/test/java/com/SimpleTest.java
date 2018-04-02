package com;

import org.junit.Test;

import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * demo
 *
 * @author ZhangShaowei on 2018/3/27 10:41
 **/
public class SimpleTest {

    public static void main(String[] args) {
        City city = new City();
        city.setId(1L);
        city.setAddress("Moon");
        city.setName("Earth Unit");

        Object province = city;

        System.out.println(province instanceof Province);

//        Field[] fields = province.getClass().getDeclaredFields();
//
//        for (Field field : fields) {
//            Annotation annotation = field.getAnnotation(Id.class);
//            if (Objects.nonNull(annotation)) {
//                field.setAccessible(true);
//                if (field.get(province) != null) {
//                    System.out.println("update");
//                } else {
//                    System.out.println("create");
//                }
//                break;
//            }
//        }


    }

    static class Province {
        protected String name;

        protected String address;

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
    }

    static class City extends Province {
        @Id
        private Long id;

        /**  */
        public Long getId() {
            return id;
        }

        /**  */
        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

}
