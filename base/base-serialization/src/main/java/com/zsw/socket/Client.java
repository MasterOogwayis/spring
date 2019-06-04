package com.zsw.socket;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author ZhangShaowei on 2019/6/4 11:03
 **/
public class Client {

    @SneakyThrows
    public static void main(String[] args) {

        User user = User.builder().name("zsw").age(1).build();

        Socket socket = new Socket("127.0.0.1", 8080);

        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject(user);

    }

}
