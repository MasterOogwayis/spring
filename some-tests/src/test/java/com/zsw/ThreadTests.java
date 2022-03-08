package com.zsw;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2022/2/15 14:20
 */
@Slf4j
public class ThreadTests {

    public static void main(String[] args) {


        Machine machine = new Machine();
        new Thread(machine).start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String command = reader.readLine();
                if (Objects.equals("exit", command)) {
                    machine.stop = true;
                    log.info("exit machine");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static class Machine implements Runnable {

        volatile boolean stop;

        @SneakyThrows
        @Override
        public void run() {
            while (!stop) {
                TimeUnit.SECONDS.sleep(1);
            }
            log.info("Exit on close: {}", this.stop);
        }
    }


    static class T extends Thread {
        static {
            System.out.println(Thread.currentThread().getName());
        }

        @Override
        public void run() {
            System.out.println("Running ...., " + Thread.currentThread().getName());
        }
    }

}
