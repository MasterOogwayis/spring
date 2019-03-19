package com.zsw.demo.pattern.prototype;

import lombok.Data;

/**
 * @author Shaowei Zhang on 2019/3/9 19:44
 **/
@Data
public class PrototypeTarget implements Cloneable {


    private String name;


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
