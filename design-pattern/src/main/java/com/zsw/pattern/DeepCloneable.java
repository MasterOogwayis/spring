package com.zsw.pattern;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.*;

/**
 * @author ZhangShaowei on 2019/3/19 14:59
 **/
public class DeepCloneable<T> implements Cloneable, Serializable {

    private static final long serialVersionUID = -6208047485273535771L;

    @Override
    public T clone() {
        return this.deepClone();
    }

    /**
     * 使用io 流克隆对象
     *
     * @return
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    private T deepClone() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        @Cleanup ObjectInputStream ois = new ObjectInputStream(bis);

        return (T) ois.readObject();
    }

}
