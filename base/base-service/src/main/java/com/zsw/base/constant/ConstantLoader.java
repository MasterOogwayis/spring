package com.zsw.base.constant;

import com.zsw.base.log.Log4jUtils;
import org.apache.commons.logging.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 描述：常数装载器
 *
 * @author ZhangShaowei on 2017/9/8 16:24
 */
public final class ConstantLoader {

    /**
     *
     */
    private static final Log log = Log4jUtils.getLog(ConstantLoader.class);

    /** */
    private ConstantLoader() {
    }

    /**
     * 描述：把指定的属性文件装载到内存，再由常数类在static块中调用初始化常数
     *
     * @param name 属性文件名字，只是名字不要路径不要后缀
     * @return 属性类，如果未找到文件或出错都返回null
     */
    public static Properties loadProperties(final String name) {
        String fileName = "/config/constant/" + name + ".properties";

        Properties properties = new Properties();
        try (InputStream inputStream = ConstantLoader.class.getResourceAsStream(fileName);) {
            properties.load(inputStream);
            return properties;
        } catch (NullPointerException e) {
            log.error("ConstantLoader classpath:" + fileName + " not find!");
            log.error(e);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            log.error(e);
        } catch (IOException e) {
            log.error("ConstantLoader Error: classpath:" + fileName + " throw IOException!");
            log.error(e);
        }

        return null;
    }
}
