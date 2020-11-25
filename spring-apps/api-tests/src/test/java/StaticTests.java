import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2020/7/9 10:39
 */
@Slf4j
public class StaticTests {
    private static int x = 100;

    public static void main(String[] args) {

        System.out.println(NettyRuntime.availableProcessors());
        System.err.println(Runtime.getRuntime().availableProcessors());

    }


    public static void testThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        for (int i = 0; i < 20; i++) {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread() + "_" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        System.err.println(System.currentTimeMillis());
        executor.shutdown();
    }


}
