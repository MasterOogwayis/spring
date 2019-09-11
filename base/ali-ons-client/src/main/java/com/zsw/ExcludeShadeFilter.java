package com.zsw;

import com.aliyun.openservices.shade.com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2019/9/11 9:56
 **/
@Slf4j
public class ExcludeShadeFilter implements TypeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        String className = metadataReader.getClassMetadata().getClassName();
        boolean scan = !Objects.equals(JSONPResponseBodyAdvice.class.getName(), className);
        if (!scan) {
            log.info("Filter Class: {}, scan: {}", className, scan);
        }

        return scan;
    }

}
