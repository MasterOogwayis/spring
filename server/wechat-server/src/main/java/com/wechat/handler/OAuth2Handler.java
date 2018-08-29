package com.wechat.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * demo 授权跳转
 *
 * Created by qianjia on 15/1/22.
 */
@Component
public class OAuth2Handler implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String href = "<a href=\"" + wxMpService.oauth2buildAuthorizationUrl(
                wxMpService.getWxMpConfigStorage().getOauth2redirectUri(),
                WxConsts.OAuth2Scope.SNSAPI_USERINFO, null) + "\">测试oauth2</a>";
        return WxMpXmlOutMessage.TEXT().content(href)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();
    }
}
