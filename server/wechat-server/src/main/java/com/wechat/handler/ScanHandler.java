package com.wechat.handler;

import javafx.event.EventType;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 扫码
 *
 * @author ZhangShaowei on 2018/8/28 14:29
 */
@Component
public class ScanHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        this.logger.debug("扫码: {}", wxMessage.getScanCodeInfo().getScanResult());

        // TODO
        if (wxMessage.getEvent() == WxConsts.EventType.SCAN) {
            return WxMpXmlOutMessage.TEXT().content("扫码:" + wxMessage.getEventKey())
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
        } else if (wxMessage.getEvent() == WxConsts.EventType.SCANCODE_PUSH) {
            return WxMpXmlOutMessage.TEXT().content("其他二维码:" + wxMessage.getScanCodeInfo().getScanResult())
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
        }

        return null;
    }
}
