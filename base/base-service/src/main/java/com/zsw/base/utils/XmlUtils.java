package com.zsw.base.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描    述:微信消息工具类
 *
 * @author : zhangshaowei
 * @version : v1.0
 * @since : v1.0
 */
public class XmlUtils {

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


    /**
     * 对象转xml
     *
     * @param obj
     * @return xml
     */
    public static String toXml(Object obj) {
        return toXml(obj, null);
    }

    /**
     * 对象转xml
     *
     * @param obj   obj
     * @param alias 节点转换
     * @return xml
     */
    public static String toXml(Object obj, Map<String, Class<?>> alias) {
        if (!CollectionUtils.isEmpty(alias)) {
            alias.forEach((key, value) -> xstream.alias(key, value));
        }
        return xstream.toXML(obj);
    }


    /**
     * xml数据流解析
     *
     * @param inputStream 输入流
     * @return Map
     * @throws Exception e
     * @author : zhangshaowei
     * @since : v1.0
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        elementList.forEach(element -> map.put(element.getName(), element.getText()));
        return map;

    }


}

