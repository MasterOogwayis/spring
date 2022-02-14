package com.zsw.utils;

import lombok.Cleanup;

import java.io.*;

/**
 * @author Shaowei Zhang on 2022/2/14 22:15
 */
public class IOUtils {

    public static <T> T copy(T target) throws IOException, ClassNotFoundException {
        var baos = new ByteArrayOutputStream();
        @Cleanup var oos = new ObjectOutputStream(baos);
        oos.writeObject(target);

        @Cleanup var objectInputStream = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        //noinspection unchecked
        return (T) objectInputStream.readObject();

    }


}
