package com.zsw;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Shaowei Zhang on 2022/4/6 22:53
 */
@Component
public class ClassPathCssMapping implements InitializingBean {

    /**
     * key -> css-mapping
     */
    private final Map<String, JSONObject> mapping = new HashMap<>();


    public JSONObject mapping(String key) {
        return this.mapping.get(key);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ClassPathResource temp = new ClassPathResource("/temp");
        File[] files = temp.getFile()
                .listFiles();

        for (File cssFile : files) {
            byte[] bytes = Files.readAllBytes(cssFile.toPath());
            String json = new String(bytes, UTF_8);
            JSONObject jsonObject = JSONObject.parseObject(json);

            String key = StringUtils.stripFilenameExtension(StringUtils.getFilename(cssFile.getName()));
            mapping.put(key, jsonObject);
        }
    }

}
