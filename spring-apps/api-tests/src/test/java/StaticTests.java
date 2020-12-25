import lombok.extern.slf4j.Slf4j;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhangShaowei on 2020/7/9 10:39
 */
@Slf4j
public class StaticTests {
    private static int x = 100;

    public static final ThreadLocal<D> CACHE = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        D d = new D();
        CACHE.set(d);

        System.gc();
        Thread.sleep(500);
        System.out.println(d);
        System.out.println(CACHE.get());

        CACHE.remove();

//        byte[] cacheData = new byte[100 * 1024 * 1024];
//        WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);
//        System.out.println("第一次GC前" + cacheData);
//        System.out.println("第一次GC前" + cacheRef.get());
//        System.gc();
//        Thread.sleep(500);
//        System.out.println("第一次GC后" + cacheData);
//        System.out.println("第一次GC后" + cacheRef.get());
//
//        cacheData = null;
//        System.gc();
//        Thread.sleep(500);
//        System.out.println("第二次GC后" + cacheData);
//        System.out.println("第二次GC后" + cacheRef.get());


    }


    public static void t() {
        //        System.out.println(NettyRuntime.availableProcessors());
//        System.err.println(Runtime.getRuntime().availableProcessors());

//        LongAdder longAdder = new LongAdder();
//        longAdder.increment();
//        System.out.println(longAdder);

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        map.put("1", "1");

        System.out.println(map.size());
    }

    private static void testJuc() {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.tryLock();
        lock.lock();
        lock.unlock();
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

    static class D{}

}
