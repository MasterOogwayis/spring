package com;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * @author ZhangShaowei on 2019/6/13 16:47
 **/
@Data
public class RemoteRequest implements Serializable {

    private Class<?> clazz;

    private String methodName;

    private Object[] datas;

    private Type[] types;


}
