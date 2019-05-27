package com.demo;

import lombok.Getter;
import lombok.Setter;
import net.jcip.annotations.GuardedBy;

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

        System.out.println(1);


    }


    private static class Td {
        @GuardedBy("this")
        public int i = 0;

        /**
         *
         */
        public int getI() {
            return i;
        }
    }
    
}
