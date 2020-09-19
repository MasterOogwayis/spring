import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2020/7/9 10:39
 */
@Slf4j
public class StaticTests {
    private static int x = 100;

    @SneakyThrows
    public static void main(String args[]) {
        Dto dto = new Dto("zsw", null);
        Dto copy = new Dto("jame", 18);
        BeanUtils.copyProperties(dto, copy);
        System.out.println(copy);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Dto {
        private String name;
        public Integer age;
    }

}
