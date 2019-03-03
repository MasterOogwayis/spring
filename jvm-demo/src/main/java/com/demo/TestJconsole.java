package com.demo;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaowei Zhang on 2019/3/3 22:03
 **/
public class TestJconsole {

    static class OOMObject {
        public byte[] placeHolder = new byte[64 * 1024];
    }

    @SneakyThrows
    public static void fillHeap(int num) {
        List<OOMObject> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        // 通过担保 一部分数据进入了老年代
        // 此方法还未出栈 list依然存活 所以 old 依然满状态
        // 在此方法外执行才有效
//        System.gc();
    }

    @SneakyThrows
    public static void main(String[] args) {
        fillHeap(1000);
        System.err.println("End...");
        // 这里调用 gc 就会清除完所有堆
        System.gc();
        Thread.sleep(60 * 1000);
    }


}
