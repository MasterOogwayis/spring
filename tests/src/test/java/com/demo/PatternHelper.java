package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZhangShaowei on 2021/11/12 10:00
 */
@Slf4j
public class PatternHelper {

    /**
     * 正则 -> ${key}
     */
    private static final Pattern PARAMS
            = Pattern.compile("\\$\\{(.*?)\\}", Pattern.DOTALL);

    /**
     * @param content  输入项
     * @param consumer 匹配结果
     */
    public static void findKey(@NonNull String content, @NonNull Consumer<String> consumer) {
        Matcher matcher = PARAMS.matcher(content);
        while (matcher.find()) {
            try {
                // ${key}, key
                consumer.accept(matcher.group(1));
            } catch (Exception e) {
                log.error("matcher error, {}, group = {}", e.getMessage(), matcher.group());
                throw new RuntimeException(e);
            }
        }
    }

}
