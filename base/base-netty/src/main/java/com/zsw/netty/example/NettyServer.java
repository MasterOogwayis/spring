package com.zsw.netty.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangShaowei on 2019/9/5 14:38
 **/
@Slf4j
public class NettyServer {

    private static final int BOSS_THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int WORKER_THREAD_SIZE = 16;

    private static final StringEncoder STRING_ENCODER = new StringEncoder(CharsetUtil.UTF_8);

    private static final StringDecoder STRING_DECODER = new StringDecoder(CharsetUtil.UTF_8);


    public static void main(String[] args) {
        start();
    }


    public static void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new DelimiterBasedFrameDecoder(8 * 1024, Delimiters.lineDelimiter()))
                                    .addLast(STRING_ENCODER)
                                    .addLast(STRING_DECODER)
                                    .addLast(new ServerHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("server has got an error: {}", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }


}
