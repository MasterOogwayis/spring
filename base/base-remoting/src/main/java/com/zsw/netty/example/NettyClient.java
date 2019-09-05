package com.zsw.netty.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author ZhangShaowei on 2019/9/5 15:01
 **/
@Slf4j
public class NettyClient {

    private static final String ENTER = "\r\n";

    private static final StringDecoder DECODER = new StringDecoder();

    private static final StringEncoder ENCODER = new StringEncoder();

    private static final ClientHandler CLIENT_HANDLER = new ClientHandler();

    public static void main(String[] args) {
        start();
    }


    public static void start() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new DelimiterBasedFrameDecoder(8 * 1024, Delimiters.lineDelimiter()))
                                    .addLast(DECODER)
                                    .addLast(ENCODER)
                                    .addLast(CLIENT_HANDLER);
                        }
                    });

            Channel channel = bootstrap.connect("127.0.0.1", 8088).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            for (; ; ) {
                String line = reader.readLine();
                if (StringUtils.isEmpty(line)) {
                    continue;
                }

                ChannelFuture channelFuture = channel.writeAndFlush(line + ENTER);
                if ("bye".equalsIgnoreCase(line)) {
                    channel.closeFuture().sync();
                    break;
                }

                channelFuture.sync();
            }

        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
        } finally {
            eventLoopGroup.shutdownGracefully();
        }


    }


}
