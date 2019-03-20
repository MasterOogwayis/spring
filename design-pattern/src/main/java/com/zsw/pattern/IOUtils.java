package com.zsw.pattern;

import com.zsw.pattern.singleton.lazy.LazyInnerClassTarget;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.*;

/**
 * @author ZhangShaowei on 2019/3/19 15:56
 **/
public class IOUtils {

    @SneakyThrows
    public static void writeObject(Serializable object, String path) {
        FileOutputStream fos = new FileOutputStream(path);
        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.flush();
    }


    @SneakyThrows
    public static Object readObject(String path) {
        FileInputStream fis = new FileInputStream(path);
        @Cleanup ObjectInputStream ois = new ObjectInputStream(fis);
        return ois.readObject();
    }

}
