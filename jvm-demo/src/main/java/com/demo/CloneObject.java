package com.demo;

import lombok.Cleanup;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.*;

/**
 * @author ZhangShaowei on 2019/3/4 11:30
 **/
@Data
public class CloneObject implements Cloneable, Serializable {


    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        // 浅拷贝
//        return super.clone();
        // 深拷贝
        return this.deepClone();
    }


    @SneakyThrows
    public Object deepClone() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        @Cleanup ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

}
