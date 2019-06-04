package com.zsw.seri;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author ZhangShaowei on 2019/6/4 14:25
 **/
public class HessianSerializer implements ISerializer {
    @Override
    @SneakyThrows
    public <T> byte[] serialize(T obj) {
        @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(baos);
        ho.writeObject(obj);
        return baos.toByteArray();
    }

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data) {
        @Cleanup ByteArrayInputStream bais = new ByteArrayInputStream(data);
        HessianInput hi = new HessianInput(bais);
        return (T) hi.readObject();
    }

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        @Cleanup ByteArrayInputStream bais = new ByteArrayInputStream(data);
        HessianInput hi = new HessianInput(bais);
        return (T) hi.readObject(clazz);
    }
}
