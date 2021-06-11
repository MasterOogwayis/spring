package com.zsw.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ZhangShaowei on 2021/6/11 9:16
 */
@Slf4j
public class EncodedFileSystemResourceLoaderDemo {

    @SneakyThrows
    public static void main(String[] args) {
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();

        Resource resource = resourceLoader.getResource("thinking-in-spring/other-tests/src/main/java/com/zsw/demo/EncodedFileSystemResourceLoaderDemo.java");
        if (resource.exists()) {
            System.out.println(IOUtils.toString(new EncodedResource(resource, UTF_8).getReader()));
        } else {
            System.err.println("not exists!");
        }

        System.out.println(EncodedFileSystemResourceLoaderDemo.class.getClassLoader());


    }

}
