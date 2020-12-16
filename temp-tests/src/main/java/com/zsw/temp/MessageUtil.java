package com.zsw.temp;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zsw.temp.aes.WXBizMsgCrypt;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 描    述	:微信消息工具类
 *
 * @author : zhangshaowei
 * @version : v1.0
 * @filename: MessageUtil.java    CopyRight (c) 2016 Company, Inc. All rights reserved.
 * @date : 2016年6月2日 下午1:32:30
 * @since : v1.0
 */
public class MessageUtil {

    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 描    述	:解析微信发来的请求（XML）   ---  明文
     *
     * @param request
     * @return
     * @throws Exception
     * @author : zhangshaowei
     * @version : v1.0
     * @date : 2016年6月2日 下午1:34:18
     * @since : v1.0
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        try (InputStream inputStream = request.getInputStream();) {
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        }
        return map;
    }


    /**
     * 描    述	:解析微信发来的请求（XML）   ---  密文
     *
     * @param request
     * @return
     * @throws Exception
     * @author : zhangshaowei
     * @version : v1.0
     * @date : 2016年6月2日 下午1:42:39
     * @since : v1.0
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseEncryptXml(
            HttpServletRequest request, String token, String key, String appId) throws Exception {
        String msgSignature = request.getParameter("msg_signature");
        String timeStamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        Map<String, String> map = new HashMap<>();
        //读取输入流中加密xml数据包
        try (InputStream inputStream = request.getInputStream();) {
            String fromXml = readInputStream(inputStream, "UTF-8");

            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, key, appId);

            String result = pc.decryptMsg(msgSignature, timeStamp, nonce, fromXml);

            SAXReader reader = new SAXReader();
            Document document = reader.read(new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)));
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        }

        return map;
    }


    /**
     * 描    述	:加密返回数据包
     *
     * @param formXml
     * @param token
     * @param key
     * @param appId
     * @return
     * @author : zhangshaowei
     * @since : v1.0
     * @throws Exception
     */
    public static String encryptXmlMessage(String formXml, String token, String key, String appId) throws Exception {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonce = getCode(9);
        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, key, appId);
        String encryptXml = pc.encryptMsg(formXml, timeStamp, nonce);
        return encryptXml;
    }


    public static String readInputStream(InputStream inStream, String charsetName) throws Exception {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();
            return new String(data, charsetName);
        }
    }

    public static String getCode(int count) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

}

