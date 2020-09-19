import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.tree.Tree;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangShaowei on 2020/7/9 10:39
 */
@Slf4j
public class StaticTests {


    @SneakyThrows
    public static void main(String[] args) {
        TreeSet<Dto> set = new TreeSet<>();

        set.add(new Dto());


    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Dto {
        private String name;

        public void test() throws InterruptedException {
            wait();
        }

    }

}
