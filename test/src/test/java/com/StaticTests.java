package com;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/10/9 10:03
 **/
public class StaticTests {

    @SneakyThrows
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

//        String join = String.join("", Files.readAllLines(Paths.get("/data/bcpt/icbc/serverPri2.key")));
//        Files.write(Paths.get("/data/bcpt/icbc/1.key"), Base64.getDecoder().decode(join.getBytes()));

        String data  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkc0lYwhrpNZHNz8LNogLTmG5+9viSnnMckvn/snWdYZMSITLyTysI1pToTvnvHfY0xZuOOho2BQbPuhDmOp48cCglTV7ci6ILm5GVU0ZXCNsXTBhfOxqNH3WEyHqBvmWgYsHhSaa1CgE6v5dux0K7t1twJ2RtD+VIz5//gz3ycC0hjI5bhrQQ78KaUa8zN+FnGoWYur1ceVeYWNDQ9umxnAF1Xuxjy3OfJUrhjLc2TExDmClZ+SH2QuLWQCsdxM34cuqxJZazFFuDKH3/5W8YPWPEeIsfpdY3fJzBljYW6XsXHsJv8dGgx/XpJLjp1cfc8wv4rdFo5LCjPI3LIjj1QIDAQAB";


        Files.write(Paths.get("/data/zsw/1.crt"), data.getBytes());


    }

    @Getter
    @Setter
    static class Dto {
        private boolean isParent;
    }

}
