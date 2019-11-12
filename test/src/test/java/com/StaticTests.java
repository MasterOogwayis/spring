package com;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/10/9 10:03
 **/
public class StaticTests {

    public static void main(String[] args) {
//        List<Dto> list = new ArrayList<>();
//        list.add(new Dto(1L, 2));
//        list.add(new Dto(1L, 1));
//        list.add(new Dto(1L, 4));
//        list.add(new Dto(1L, 3));
//        list.add(new Dto(2L, 2));
//        list.add(new Dto(2L, 2));
//        list.add(new Dto(2L, 2));
//        list.add(new Dto(2L, 2));
//        list.add(new Dto(3L, 2));
//        list.add(new Dto(3L, 2));
//        list.add(new Dto(3L, 2));
//        list.add(new Dto(3L, 2));

//        System.out.println(String.join(":", "a", "b", "c", "d"));
//
//
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//
//        Dto d = new Dto();


        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100000; i++) {
            ResponseEntity<String> forEntity = restTemplate.getForEntity("http://39.100.39.63:5000", String.class);
            System.out.println(forEntity);
        }

    }

    @Getter
    @Setter
    static class Dto {
        private boolean isParent;
    }

}
