package com.zsw.design.decorator;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 装饰器模式
 *        1. 注重覆盖，扩展
 *        2. 装饰器和被装饰器都实现同一个接口主要目的是为了扩展只有依旧保留OOP关系，同宗同源
 *        3. 蛮子 is-a 关系
 *
 * @author ZhangShaowei on 2018/10/22 12:27
 **/
public class TestDecorator {

    public static void main(String[] args) {
        DataInputStream dis = new DataInputStream(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });
    }


}
