package com.zsw.lesson.l5;

/**
 * @author ZhangShaowei on 2021/4/12 13:59
 */
public class L5Tests {

    public static void main(String[] args) {
        Passenger a = new ForeignerPassenger();
        Passenger b = new ChinesePassenger();

        long current = System.currentTimeMillis();
        for (int i = 0; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.err.println(temp - current);
                current = temp;
            }
            Passenger c = (i < 1_000_000_000) ? a : b;
            c.passThroughImmigration();
        }


    }

}
