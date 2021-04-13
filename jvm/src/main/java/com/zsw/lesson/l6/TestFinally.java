package com.zsw.lesson.l6;

/**
 * @author ZhangShaowei on 2021/4/12 16:15
 */
public class TestFinally {

    public static void main(String[] args) {
        try {
            System.err.println(test1());;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally ...");
        }
    }


    /**
     * @return 1
     */
    private static int test1() {
        try {
            throw new E2("这个异常会被吞");
        } catch (E1 e) {
            e.printStackTrace();
            return 2;
        } finally {
            return 1;
        }
    }

    static class E1 extends RuntimeException {
        public E1() {
        }

        public E1(String message) {
            super(message);
        }

        public E1(String message, Throwable cause) {
            super(message, cause);
        }

        public E1(Throwable cause) {
            super(cause);
        }

        public E1(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }


    static class E2 extends RuntimeException {
        public E2() {
            super();
        }

        public E2(String message) {
            super(message);
        }

        public E2(String message, Throwable cause) {
            super(message, cause);
        }

        public E2(Throwable cause) {
            super(cause);
        }

        protected E2(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }


}
