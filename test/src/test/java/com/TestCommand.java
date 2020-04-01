package com;

import java.io.IOException;

/**
 * @author ZhangShaowei on 2020/3/26 15:52
 */
public class TestCommand {

    private static ProcessBuilder processBuilder = new ProcessBuilder();

    private static boolean dis;

    public static void main(String[] args) throws IOException, InterruptedException {

        if (!dis) {
            System.err.println(123456789);
        }

//        processBuilder.redirectErrorStream(true);
//
//        Process start = processBuilder.command("ping 192.168.1.12").start();
//
//        byte[] bytes = StreamUtils.copyToByteArray(start.getInputStream());
//
//        System.out.println(new String(bytes));
//
//        start.waitFor();
//        start.destroy();


    }

}
