package com.zsw.demo.serializer.protostuff;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author ZhangShaowei on 2019/9/26 15:14
 **/
public class ProtostuffEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        byte[] data = ProtostuffSerializer.serialize(msg);
        out.writeBytes(data);
    }
}
