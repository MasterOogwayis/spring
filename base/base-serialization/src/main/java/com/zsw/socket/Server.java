package com.zsw.socket;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

/**
 * @author ZhangShaowei on 2019/6/4 11:00
 **/
public class Server {

    @SneakyThrows
    public static void main(String[] args) {

        ServerSocket serverSocket = new ServerSocket(8080);

        Socket socket = serverSocket.accept();

        @Cleanup ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        User user = (User) ois.readObject();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.obj"));
        oos.writeObject(user);

        System.out.println(user);


    }

}
