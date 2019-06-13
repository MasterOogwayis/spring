package com.zsw.test.nio;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Administrator on 2019/6/13 22:00
 **/
@Slf4j
@AllArgsConstructor
public class NioClientHandler implements Runnable {

    private static final Charset utf8 = Charset.forName("utf-8");

    private String address;

    private int port;

    private String message;

    @Override
    public void run() {
        Selector selector;
        try {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);

            selector = Selector.open();
            sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, new Buffers(512, 512));

            sc.connect(new InetSocketAddress(address, port));
            while (!sc.finishConnect()) {
                ;
            }
            log.info("已连接到服务端");
        } catch (IOException e) {
            log.error("客户端连接失败：{}", e.getMessage());
            return;
        }


        try {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    Buffers buffers = (Buffers) key.attachment();

                    ByteBuffer writeBuffer = buffers.writeBuffer;
                    ByteBuffer readBuffer = buffers.readBuffer;

                    SocketChannel sc = (SocketChannel) key.channel();

                    if (key.isReadable()) {
                        sc.read(readBuffer);
                        readBuffer.flip();

                        CharBuffer charBuffer = utf8.decode(readBuffer);
                        log.info("接收到服务器消息：{}", Arrays.toString(charBuffer.array()));

                        readBuffer.clear();

                    } else if (key.isWritable()) {
                        writeBuffer.put((message + " " + i++).getBytes(utf8));
                        writeBuffer.flip();
                        while (writeBuffer.hasRemaining()) {
                            sc.write(writeBuffer);
                        }
                        writeBuffer.clear();
                    }
                }
                Thread.sleep(500);
            }
        } catch (IOException e) {
            log.error("遇到连接错误: {}", e.getMessage());
        } catch (InterruptedException e) {
            log.error("客户端已被终止:{}", e.getMessage());
        } finally {
            try {
                selector.close();
            } catch (IOException e) {
                log.error("关闭 selector 出错");
            }
            log.info("客户端已停止");
        }


    }


}
