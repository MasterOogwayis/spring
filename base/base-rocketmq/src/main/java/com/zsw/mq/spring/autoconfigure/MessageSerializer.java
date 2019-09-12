package com.zsw.mq.spring.autoconfigure;

import org.springframework.lang.Nullable;

import java.io.IOException;

/**
 * 序列化和反序列化
 *
 * @author ZhangShaowei on 2019/9/11 16:50
 **/
public interface MessageSerializer {

    byte[] serialize(@Nullable Object object);

    <T> T deserialize(@Nullable byte[] body, Class<T> clazz) throws IOException;

}
