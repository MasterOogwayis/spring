package com.wechat.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

/**
 * @author ZhangShaowei on 2018/8/29 13:18
 **/
@Component
public class ImageHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        try {
            URL url = new URL(wxMessage.getPicUrl());
            Files.copy(
                    url.openStream(),
                    Paths.get("data", wxMessage.getFromUser(), wxMessage.getMediaId() + ".jpg"),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
