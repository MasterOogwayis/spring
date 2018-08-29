package com.ui;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.wifi.WxMpWifiShopListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2018/8/20 14:07
 **/
@RestController
public class TestController {

    /**
     *
     */
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("text")
    public String text(String openid, String message) {

        WxMpKefuMessage textMessage = WxMpKefuMessage.TEXT().toUser(openid).content(message).build();
        try {
            wxMpService.getKefuService().sendKefuMessage(textMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return "success";
    }


    @GetMapping("test")
    public Object test(String openid) throws WxErrorException {
        return this.wxMpService.getUserService().userInfo(openid);
    }


}
