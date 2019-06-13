package com.zsw.test.nio;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @author Administrator on 2019/6/13 21:29
 **/
@Slf4j
@AllArgsConstructor
public class NioServerHandler implements Runnable {

    private static final Charset utf8 = Charset.forName("utf-8");

    private static Random rn = new Random();

    private int port;


    @Override
    public void run() {
        ServerSocketChannel serverSocketChannel;
        Selector selector;

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(8088));

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            log.info("服务器已启动...");
        } catch (IOException e) {
            log.error("服务器启动失败: {}", e.getMessage());
            return;
        }


        try {

            while (!Thread.currentThread().isInterrupted()) {
                int select = selector.select();
                if (select == 0) {
                    continue;
                }

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    try {
                        if (key.isAcceptable()) {
                            SocketChannel accept = serverSocketChannel.accept();
                            accept.configureBlocking(false);
                            accept.register(selector, SelectionKey.OP_READ, new Buffers(512, 512));

                            log.info("一个客户端连接了：{}", accept.getRemoteAddress());
                        } else if (key.isReadable()) {
                            SocketChannel sc = (SocketChannel) key.channel();
                            Buffers buffers = (Buffers) key.attachment();

                            ByteBuffer readBuffer = buffers.readBuffer;
                            ByteBuffer writeBuffer = buffers.writeBuffer;

                            sc.read(readBuffer);
                            readBuffer.flip();

                            CharBuffer charBuffer = utf8.decode(readBuffer);

                            log.info("Receive message from client: {}", Arrays.toString(charBuffer.array()));

                            // 复位 接下来还要读取
                            readBuffer.rewind();

                            writeBuffer.put("Echo from Server:".getBytes(utf8));
                            writeBuffer.put(readBuffer);

                            readBuffer.clear();

                            // 设置通道写事件
                            key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                        } else if (key.isWritable()) {
                            SocketChannel sc = (SocketChannel) key.channel();
                            Buffers buffers = (Buffers) key.attachment();
                            ByteBuffer writeBuffer = buffers.writeBuffer;

                            writeBuffer.flip();
                            int len = 0;
                            while (writeBuffer.hasRemaining()) {
                                len = sc.write(writeBuffer);
                                if (len == 0) {
                                    break;
                                }
                            }
                            writeBuffer.compact();
                            if (len != 0) {
                                key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                            }


                        }
                    } catch (IOException e) {
                        log.error("客户端已中断：{}", e.getMessage());
                        key.cancel();
                        key.channel().close();
                    }

                    Thread.sleep(rn.nextInt(500));
                }
            }

        } catch (IOException e) {
            log.error("服务器 Selector 出错 ：{}", e.getMessage());
        } catch (InterruptedException e) {
            log.error("服务器已被停止：{}", e.getMessage());
        } finally {
            try {
                selector.close();
            } catch (IOException e) {
                log.error("关闭 selector 失败：{}", e.getMessage());
            }
            log.info("服务器已关闭");
        }
    }



}
