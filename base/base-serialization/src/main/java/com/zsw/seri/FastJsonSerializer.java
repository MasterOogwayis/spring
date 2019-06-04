package com.zsw.seri;

import com.alibaba.fastjson.JSON;

/**
 * @author ZhangShaowei on 2019/6/4 14:15
 **/
public class FastJsonSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        return JSON.toJSONString(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data) {
        throw new IllegalArgumentException("class 类型丢失");
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(new String(data), clazz);
    }

}
