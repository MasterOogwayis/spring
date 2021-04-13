package com.zsw.lesson.l7;


import java.lang.reflect.Method;

/**
 * 在运行指令中添加如下两个虚拟机参数：
 * -Djava.lang.Integer.IntegerCache.high=128
 * -Dsun.reflect.noInflation=true
 * -XX:TypeProfileWidth=3 默认2 虚拟机记录的类型数目
 *
 * @author ZhangShaowei on 2021/4/13 9:40
 */
class ReflectionTests {

    public static void main(String[] args) throws Exception {
        Method target = ReflectionTests.class.getMethod("target", int.class);
        target.setAccessible(true);
        // 内联瓶颈，提前循环 2000 次反射调用其他2个方法，扰乱 Method.invoke 方法的类型 profile
        // 会导致性能开销显著上升
        // -XX:TypeProfileWidth=3
        polluteProfile();

        long current = System.currentTimeMillis();
        Object[] arr = new Object[]{128};
        for (int i = 0; i < 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            // -Djava.lang.Integer.IntegerCache.high=128
            // 大量装箱也会影响性能，这里将缓存扩大到128，会发现时间明显加快了
//            target.invoke(null, 128);
            // 第二个参数是可变参数，即数组。提前创建数组也能极大加快性能
            target.invoke(null, arr);
//            target(i);
        }

        System.out.println("end...");
    }


    /**
     * 测试 20 亿次调用性能损耗
     *
     * @param i
     */
    public static void target(int i) {
//        new Exception(i + "").printStackTrace();
    }

    public static void polluteProfile() throws Exception {
        Method method1 = ReflectionTests.class.getMethod("target1", int.class);
        Method method2 = ReflectionTests.class.getMethod("target2", int.class);
        for (int i = 0; i < 2000; i++) {
            method1.invoke(null, 0);
            method2.invoke(null, 0);
        }
    }

    public static void target1(int i) {
    }

    public static void target2(int i) {
    }

}
