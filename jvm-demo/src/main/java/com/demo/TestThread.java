package com.demo;


/**
 * @author ZhangShaowei on 2019/5/7 13:29
 **/
public class TestThread {

    public static void main(String[] args) {

//        AtomicStampedReference<Long> atomicStampedReferenc = new AtomicStampedReference<>(0L, 1);
//
//        AtomicLong atomicLong = new AtomicLong(0L);
//        long i = atomicLong.incrementAndGet();
//        System.err.println(i);
//        System.out.println(atomicLong.get());

        System.out.println(1);


    }


    private static class Td {
        public int i = 0;

        /**
         *
         */
        public int getI() {
            return i;
        }
    }

}
