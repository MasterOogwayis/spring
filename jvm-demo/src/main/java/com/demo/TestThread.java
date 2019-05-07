package com.demo;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

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

        Td td = new Td();
        AtomicReference<Td> atomicReference = new AtomicReference<>(td);
        boolean b = atomicReference.compareAndSet(new Td(), new Td());
        System.err.println(b);

    }


    private static class Td {

    }
    
}
