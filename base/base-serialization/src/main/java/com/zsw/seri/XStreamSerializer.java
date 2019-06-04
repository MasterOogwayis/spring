package com.zsw.seri;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author ZhangShaowei on 2019/6/4 14:30
 **/
public class XStreamSerializer implements ISerializer {

    XStream xStream = new XStream(new DomDriver());

    @Override
    public <T> byte[] serialize(T obj) {
        return xStream.toXML(obj).getBytes();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data) {
        return (T) xStream.fromXML(new String(data));
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return deserialize(data);
    }
}
