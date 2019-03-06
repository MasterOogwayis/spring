package com.demo;

import lombok.Data;

import java.io.*;

/**
 * @author ZhangShaowei on 2019/3/4 11:30
 **/
@Data
public class CloneObject implements Cloneable, Serializable {

    private Dto dto;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        // 浅拷贝
//        return super.clone();
        // 深拷贝
        return this.deepClone();
    }


    public Object deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
