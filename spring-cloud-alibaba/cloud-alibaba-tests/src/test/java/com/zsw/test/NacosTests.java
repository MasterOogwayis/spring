package com.zsw.test;

import com.alibaba.cloud.nacos.parser.NacosDataParserHandler;
import org.springframework.core.env.PropertySource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangShaowei on 2020/10/19 17:09
 */
public class NacosTests {

    public static void main(String[] args) throws IOException {
        String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<biology id=\"0\">\n" +
                "    <zoology>\n" +
                "        <biology id=\"1\">\n" +
                "            <name>Cat</name>\n" +
                "        </biology>\n" +
                "        <biology id=\"2\">\n" +
                "            <name>Dog</name>\n" +
                "        </biology>\n" +
                "    </zoology>\n" +
                "    <phytology>\n" +
                "        <biology id=\"3\">\n" +
                "            <name>Tree</name>\n" +
                "        </biology>\n" +
                "        <biology id=\"4\">\n" +
                "            <name>Shrub</name>\n" +
                "        </biology>\n" +
                "    </phytology>\n" +
                "</biology>";
        List<PropertySource<?>> propertySources = NacosDataParserHandler.getInstance().parseNacosData("dataId", data, "xml");
        System.out.println(propertySources);
    }

}
