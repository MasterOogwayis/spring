package com.zsw.test.nio;

import lombok.SneakyThrows;

/**
 * @author Administrator on 2019/6/13 22:15
 **/
public class NioServer {

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(new NioServerHandler(8088));

        thread.start();

        Thread.sleep(30 * 1000);

        thread.interrupt();

    }

}
