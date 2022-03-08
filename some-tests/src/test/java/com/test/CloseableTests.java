package com.test;

import com.zsw.io.ShouldClose;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author ZhangShaowei on 2022/2/15 14:17
 */
@Slf4j
public class CloseableTests {


    public static void main(String[] args) throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze sneeze) {
            System.out.println("Caught Sneeze");
        } finally {
            System.out.println("Hello World!");
        }


    }


    static class Annoyance extends Exception {

    }

    static class Sneeze extends Annoyance {

    }

    private static void m() throws IOException {
        try (Closeable c = () -> System.out.println("close")) {
            System.out.println("open");
        }
    }

    private static void t1() {
        try (ShouldClose shouldClose = new ShouldClose()) {
            log.info("ShouldClose: {}", shouldClose);
        }
    }


}
