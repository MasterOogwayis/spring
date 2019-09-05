package com.zsw.netty.example;

import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.util.Date;

/**
 * @author ZhangShaowei on 2019/9/5 14:49
 **/
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private static final String ENTER = "\r\n";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("a client has connected to server: {}", ctx.channel().remoteAddress());
        ctx.write("Welcome to : " + InetAddress.getLocalHost().getHostName() + " ." + ENTER);
        ctx.write("It is " + new Date() + " now.");
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String response;
        boolean close = false;
        if (StringUtils.isEmpty(msg)) {
            response = "Please type something. " + ENTER;
        } else if ("bye".equalsIgnoreCase(msg)) {
            response = "Have a good day. " + ENTER;
            close = true;
        } else {
            response = "Server has received you message: '" + msg +"'." + ENTER;
        }
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);
        if (close) {
            log.info("A client logout: {}", channelFuture.channel().remoteAddress());
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("channel exception caught: {}", cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }
}
