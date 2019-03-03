package com.demo;

/**
 * monitorenter / monitorexit
 *
 * 对象结构  对象头 Header(锁的原理就在这里面)  + 数据 instanceData + 填充区域 Padding
 *
 * @author Shaowei Zhang on 2019/3/3 15:46
 **/
public class TestLock {

    private final byte[] lock = new byte[0];

    public String lock1() {
        int i = 0;
        synchronized (this.lock) {
            i++;
        }
        return String.valueOf(i);

    }

    public synchronized String lock2() {
        int i = 0;
        i++;
        return String.valueOf(i);
    }



}
