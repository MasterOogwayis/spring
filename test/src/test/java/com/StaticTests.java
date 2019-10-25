package com;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

        System.out.println(String.join(":", "a", "b", "c", "d"));


        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        Dto d = new Dto();

    }

    @Getter
    @Setter
    static class Dto {
        private boolean isParent;
    }

}
