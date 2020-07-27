import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2020/7/9 10:39
 */
@Slf4j
public class StaticTests {

    @SneakyThrows
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());


    }


}
