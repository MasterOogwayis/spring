package com.zsw.lesson.l6;

/**
 * 异常测试
 * <p>
 * Throwable
 * |
 * --------------------
 * |                   |
 * Exception            Error
 * |
 * RuntimeException
 *
 * @author ZhangShaowei on 2021/4/12 15:19
 */
public class L6Tests {


    public static void main(String[] args) {
        System.out.println(new L6Tests().test1());

        try (
                TestCloseable t1 = new TestCloseable("t1");
                TestCloseable t2 = new TestCloseable("t1");
        ) {
            throw new RuntimeException("Initial");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int tryBlock;
    private int catchBlock;
    private int finallyBlock;
    private int methodExit;

    public int test1() {
        try {
            tryBlock = 0;
            return 0;
        } catch (Exception e) {
            catchBlock = 1;
            return 1;
        } finally {
            finallyBlock = 2;
            return 2;
        }
//        methodExit = 3;
//        return num;
    }
}



