import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author ZhangShaowei on 2020/7/9 10:39
 */
@Slf4j
public class StaticTests {


    @SneakyThrows
    public static void main(String[] args) {

        LocalDate with = LocalDate.of(2020, 5, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
        System.out.println(with);


    }

}
