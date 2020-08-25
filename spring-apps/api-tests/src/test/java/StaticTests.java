import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangShaowei on 2020/7/9 10:39
 */
@Slf4j
public class StaticTests {
    private static int x = 100;

    public static void main(String args[]) {
        StaticTests hs1 = new StaticTests();
        hs1.x++;
        StaticTests hs2 = new StaticTests();
        hs2.x++;
        hs1 = new StaticTests();
        hs1.x++;
        StaticTests.x--;
        System.out.println("x=" + x);
    }

}
