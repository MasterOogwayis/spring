package com.zsw.design.prototype;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.*;

/**
 * @author Shaowei Zhang on 2019/3/19 22:45
 **/
public class DeepCloneable<T> implements Cloneable, Serializable {
    private static final long serialVersionUID = -7005807263366546114L;



    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public T clone(){
        return (T) super.clone();
    }


    @SneakyThrows
    @SuppressWarnings("unchecked")
    public T deepClone(){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        @Cleanup ObjectInputStream ois = new ObjectInputStream(bis);
        return (T) ois.readObject();
    }



}
