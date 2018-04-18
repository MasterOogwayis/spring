package com;

import ch.qos.logback.classic.Level;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.persistence.bean.SaleOrder;
import org.apache.commons.lang.math.RandomUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * test
 *
 * @author ZhangShaowei on 2018/4/3 10:05
 **/
public class StaticTests {

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static final RestTemplate restTemplate = new RestTemplate();

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(StaticTests.class);

    static {
        ch.qos.logback.classic.Logger root =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Long.MAX_VALUE);
//
//        List<Room> rooms = new ArrayList<>();
//        rooms.add(new Room(1L, 10));
//        rooms.add(new Room(1L, 20));
//        rooms.add(new Room(1L, 30));
//        rooms.add(new Room(1L, 40));
//        rooms.add(new Room(1L, 50));
//
//        Map<Long, Integer> map1  = rooms.stream().collect(Collectors.groupingBy(Room::getId, Collectors.summingInt(Room::getNumber)));
//        Map<Long, List<Room>> map2  = rooms.stream().collect(Collectors.groupingBy(Room::getId, Collectors.toList()));
//
//        System.out.println(map1);

//        System.out.println(new SaleOrder().getClass().getName());


//        if (logger.isDebugEnabled()) {
//            System.err.println("debug");
//            return;
//        }
//
//
//        for (int i = 0; i < 1000; i++) {
//
//            // 每隔 30s 连续 5s 的峰值
//            if (i % 30 < 5) {
//                for (int j = 0; j < 50; j++) {
//                    get(RandomUtils.nextInt(300));
//                    Thread.sleep(50L);
//                }
//            } else {
//                logger.info("低值 {}, i={}", gson.toJson(get(RandomUtils.nextInt(300))), i);
//                Thread.sleep(1000L);
//            }
//        }



    }

    public static Object get(Integer id){
        try {
            return restTemplate.getForObject(
                    "http://www.demo.com:3334/api/get?id=" + id, Object.class);
        } catch (Exception e) {
            logger.error("id={}, message={}", id, e.getMessage());
            return e.getMessage();
        }
    }

    static class Room {
        private Long id;

        private Integer number;

        /**  */
        public Long getId() {
            return id;
        }

        /**  */
        public void setId(Long id) {
            this.id = id;
        }

        /**  */
        public Integer getNumber() {
            return number;
        }

        /**  */
        public void setNumber(Integer number) {
            this.number = number;
        }

        public Room(Long id, Integer number) {
            this.id = id;
            this.number = number;
        }
    }

}
