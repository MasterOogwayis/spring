package com.client;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.spring.SpringMultipartEncodedDataProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

/**
 * @author ZhangShaowei on 2017/6/13 17:10
 */

public class MyEncoder extends FormEncoder {

    private final Encoder delegate;

    public MyEncoder() {
        this(new Default());
    }

    public MyEncoder(Encoder delegate) {
        this.delegate = delegate;
    }

    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if(!bodyType.equals(MultipartFile.class)) {
            this.delegate.encode(object, bodyType, template);
        } else {
            MultipartFile file = (MultipartFile)object;
            Map<String, Object> data = Collections.singletonMap(file.getName(), object);
            (new SpringMultipartEncodedDataProcessor()).process(data, template);
        }
    }

}
