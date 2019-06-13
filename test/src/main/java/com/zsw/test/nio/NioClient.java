package com.zsw.test.nio;

/**
 * @author Administrator on 2019/6/13 22:16
 **/
public class NioClient {

    public static void main(String[] args) {
        Thread client = new Thread(new NioClientHandler("127.0.0.1", 8088, "您好"));
        client.start();

    }


}
