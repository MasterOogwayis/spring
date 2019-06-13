package com.zsw.test.nio;

import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * @author Administrator on 2019/6/13 21:42
 **/
@Getter
public class Buffers {

    ByteBuffer writeBuffer;

    ByteBuffer readBuffer;

    public Buffers(int writeCapacity, int readCapacity) {
        this.writeBuffer = ByteBuffer.allocate(writeCapacity);
        this.readBuffer = ByteBuffer.allocate(readCapacity);
    }
}
