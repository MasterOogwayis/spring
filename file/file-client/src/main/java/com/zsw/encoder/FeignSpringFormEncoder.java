package com.zsw.encoder;

import org.springframework.cloud.openfeign.support.SpringEncoder;


/**
 * @author ZhangShaowei on 2017/6/13 17:10
 */

public class FeignSpringFormEncoder { //extends SpringEncoder {

//    /**
//     *
//     */
//    private FeignMultipartEncodedDataProcessor dataProcessor;
//
//    /**
//     * @param messageConverters HttpMessageConverters
//     */
//    public FeignSpringFormEncoder(final ObjectFactory<HttpMessageConverters> messageConverters) {
//        super(messageConverters);
//        dataProcessor = new FeignMultipartEncodedDataProcessor(messageConverters);
//    }
//
//    /**
//     * @param object   data
//     * @param bodyType Class
//     * @param template RequestTemplate
//     * @throws EncodeException e
//     */
//    @Override
//    public void encode(
//            final Object object, final Type bodyType, final RequestTemplate template) throws EncodeException {
//        if (isMultipartFile(object)) {
//            this.dataProcessor.process(object, template);
//        } else {
//            super.encode(object, bodyType, template);
//        }
//    }
//
//    /**
//     * 是否是文件上传
//     *
//     * @param data data
//     * @return true or false
//     */
//    private boolean isMultipartFile(final Object data) {
//        return data instanceof MultipartFile || (data.getClass().isArray()
//                && MultipartFile.class.isAssignableFrom(data.getClass().getComponentType()));
//    }

}
