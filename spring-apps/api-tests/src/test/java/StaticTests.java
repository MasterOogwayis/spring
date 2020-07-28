import lombok.Data;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2020/7/9 10:39
 */
@Slf4j
public class StaticTests {

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            System.out.println(1);
            TimeUnit.SECONDS.sleep(5);
        }
    }


    @Data
    static class Dto {
        private String name;
    }


}
