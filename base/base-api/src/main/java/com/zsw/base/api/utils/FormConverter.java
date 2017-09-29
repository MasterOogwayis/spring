package com.zsw.base.api.utils;

import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：Form表单转换器，此类基于Gson工作。<br>
 * 默认构造函数只定义了把日期数据按默认时间戳格式转换为日期对象，<br>
 * 若要自定义日期格式或其它属性格式的转换，请传入自定义的Gson对象。
 */
public class FormConverter {
    /**
     *
     */
    protected static Logger log = (Logger) LoggerFactory.getLogger(FormConverter.class);

    /**
     *
     */
    protected Gson gson;

    /**
     * 描述：构造函数
     */
    public FormConverter() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * 描述：构造函数
     *
     * @param gson 自定义了数据转换格式的Gson
     */
    public FormConverter(final Gson gson) {
        this.gson = gson;
    }

    /**
     * 描述：Form表单转成gson字符串<br>
     * 支持数组和List，表单属性会去掉前后空格，空字符串按null处理
     *
     * @param request request
     * @param clazz   要转换的类模板
     */
    public String convertToGson(final HttpServletRequest request, final Class<?> clazz) {
        Map<String, Object> map = new HashMap<>();
        Enumeration<?> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = enu.nextElement().toString();
            if ("".equals(key)) continue;

            if ("_method".equals(key)) continue; // 排除spring restful 使用的_method

            String key1 = "get" + key.replaceFirst(key.substring(0, 1), key.substring(0, 1).toUpperCase());
            String key2 = "is" + key.replaceFirst(key.substring(0, 1), key.substring(0, 1).toUpperCase());

            String[] values = request.getParameterValues(key);
            try {
                String typeName;
                try {
                    typeName = clazz.getMethod(key1).getReturnType().getName();
                } catch (NoSuchMethodException e) {
                    // 如果查找get的方法失败，再查找is的方法，如果都失败抛出异常
                    typeName = clazz.getMethod(key2).getReturnType().getName();
                }
                if (typeName.contains("[") || typeName.contains("List")) {
                    List<String> newValues =
                            Arrays.stream(values).filter(StringUtils::hasText)
                                    .map(String::trim).collect(Collectors.toList());
                    map.put(key, newValues);
                } else {
                    map.put(key, values[0].equals("") ? null : values[0].trim());
                }
            } catch (SecurityException e) {
                log.warn("获取 " + clazz.getName() + " 类的 " + key + " 属性出现[" + e.toString() + "]异常");
            } catch (NoSuchMethodException e) {
                log.warn("获取 " + clazz.getName() + " 类的 " + key + " 属性出错，请检查此属性是否有对应的getter，setter方法");
            }
        }
        String jsonBody = gson.toJson(map);
        log.debug("Form表单序列化后的Gson字符串为：" + jsonBody);
        return jsonBody;
    }

    /**
     * 描述：Form表单转成对象<br>
     * 支持数组和List，表单属性会去掉前后空格，空字符串按null处理
     *
     * @param request request
     * @param clazz   要转换成的对象类
     * @return 转换后的对象
     */
    public <T> T convertToBean(final HttpServletRequest request, final Class<T> clazz) {
        String jsonBody = convertToGson(request, clazz);
        return gson.fromJson(jsonBody, clazz);
    }

}
