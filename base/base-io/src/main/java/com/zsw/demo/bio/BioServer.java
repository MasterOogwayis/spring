package com.zsw.lesson.bio;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangShaowei on 2019/9/19 15:42
 **/
@Slf4j
public class BioServer {

    @SneakyThrows
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4,
                20,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new ServerThreadFactory()
        );

        ServerSocket serverSocket = new ServerSocket(8088);

        while (true) {
            Socket socket = serverSocket.accept();
            executor.execute(new ServerHandler(socket));
        }

    }


    @AllArgsConstructor
    static class ServerHandler implements Runnable {
        Socket socket;

        @Override
        public void run() {
            PrintWriter writer = null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
                writer = new PrintWriter(this.socket.getOutputStream(), true);
                while (true) {
                    String body = reader.readLine();
                    if (StringUtils.isEmpty(body)) {
                        continue;
                    }
                    if ("bye".equalsIgnoreCase(body)) {
                        socket.close();
                        break;
                    }
                    log.info("reveive message: {}", body);
                    writer.println("echo: " + body);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (!Objects.isNull(writer)) {
                    writer.close();
                }
            }



        }
    }


    static class ServerThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        ServerThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-server-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
