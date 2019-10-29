package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZhangShaowei on 2019/10/25 9:43
 **/
@RunWith(Parameterized.class)
public class JunitTests {

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[2][0]; // repeat count which you want
    }

    @Test(expected = TestException.class)
    public void test() {

        if (ThreadLocalRandom.current().nextInt() % 2 == 0) {
            throw new TestException("error");
        }


    }






}
