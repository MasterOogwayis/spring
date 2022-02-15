package com.demo.spring.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Shaowei Zhang on 2021/6/20 13:33
 */
public class ResourceUtils {

    @SneakyThrows
    public static String getContext(Resource resource) {
        return IOUtils.toString(new EncodedResource(resource, UTF_8).getReader());
    }

}
