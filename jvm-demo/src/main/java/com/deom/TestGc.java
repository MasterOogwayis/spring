package com.deom;

import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2019/2/25 9:21
 **/
public class TestGc {

    @SneakyThrows
    public static void main(String[] args) {
        byte[] data1 = new byte[2 * 1024 * 1024];
        byte[] data2 = new byte[2 * 1024 * 1024];
        byte[] data3 = new byte[2 * 1024 * 1024];
        byte[] data4 = new byte[4 * 1024 * 1024];

        System.gc();
    }


}
