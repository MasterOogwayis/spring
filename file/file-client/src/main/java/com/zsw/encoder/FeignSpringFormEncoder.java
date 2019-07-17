package com.zsw.encoder;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;


/**
 * @author ZhangShaowei on 2017/6/13 17:10
 */

public class FeignSpringFormEncoder extends SpringEncoder {

    /**
     *
     */
    private FeignMultipartEncodedDataProcessor dataProcessor;

    /**
     * @param messageConverters HttpMessageConverters
     */
    public FeignSpringFormEncoder(final ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
        dataProcessor = new FeignMultipartEncodedDataProcessor(messageConverters);
    }

    /**
     * @param object   data
     * @param bodyType Class
     * @param template RequestTemplate
     * @throws EncodeException e
     */
    @Override
    public void encode(
            final Object object, final Type bodyType, final RequestTemplate template) throws EncodeException {
        if (isMultipartFile(object)) {
            this.dataProcessor.process(object, template);
        } else {
            super.encode(object, bodyType, template);
        }
    }

    /**
     * 是否是文件上传
     *
     * @param data data
     * @return true or false
     */
    private boolean isMultipartFile(final Object data) {
        return data instanceof MultipartFile || (data.getClass().isArray()
                && MultipartFile.class.isAssignableFrom(data.getClass().getComponentType()));
    }

}
