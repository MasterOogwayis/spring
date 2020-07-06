package com.zsw.commons.support;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 防痴呆
 *
 * @author ZhangShaowei on 2020/6/17 19:05
 */
@Slf4j
public final class Duplicate {

    public static void checkDuplicate(Class<?> clazz) {
        checkDuplicate(clazz.getName().replace('.', '/') + ".class");
    }


    public static void checkDuplicate(String path) {
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
            Set<String> files = new HashSet<>();

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (Objects.nonNull(url)) {
                    String file = url.getFile();
                    if (StringUtils.isNotBlank(file)) {
                        files.add(file);
                    }
                }
            }
            if (files.size() > 1) {
                log.error("Duplicate files {} in {} jar {}", path, files.size(), files);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    public static void main(String[] args) {
        checkDuplicate("META-INF/spring.factories");
    }

}
