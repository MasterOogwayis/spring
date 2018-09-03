package com.wechat.controller;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2018/8/31 15:02
 **/
@RestController
@RequestMapping("/wechat/menu")
@AllArgsConstructor
public class WeChatMenuController {

    private WxMpService wxMpService;


    @GetMapping
    public WxMpMenu getMenu() throws WxErrorException {
        return this.wxMpService.getMenuService().menuGet();
    }


    @PostMapping
    public String createMenu() throws WxErrorException {
        return this.wxMpService.getMenuService().menuCreate(this.menuData());
    }


    public WxMenu menuData() {
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType(WxConsts.MenuButtonType.CLICK);
        button1.setName("点我");
        button1.setKey("click");

        WxMenuButton button2 = new WxMenuButton();
        button2.setType(WxConsts.MenuButtonType.VIEW);
        button2.setName("百度搜索");
        button2.setUrl("https://www.baidu.com/");

        WxMenuButton button3 = new WxMenuButton();
        button3.setName("菜单");



        WxMenuButton button31 = new WxMenuButton();
        button31.setType(WxConsts.MenuButtonType.SCANCODE_PUSH);
        button31.setName("扫码");
        button31.setKey("scancode_push");

        WxMenuButton button32 = new WxMenuButton();
        button32.setType(WxConsts.MenuButtonType.PIC_WEIXIN);
        button32.setName("相册");
        button32.setKey("pic_weixin");

        WxMenuButton button33 = new WxMenuButton();
        button33.setType(WxConsts.MenuButtonType.VIEW);
        button33.setName("授权跳转");
        button33.setUrl(
                this.wxMpService.oauth2buildAuthorizationUrl(
                        wxMpService.getWxMpConfigStorage().getOauth2redirectUri(),
                        WxConsts.OAuth2Scope.SNSAPI_USERINFO, null
                )
        );

        button3.getSubButtons().add(button31);
        button3.getSubButtons().add(button32);
        button3.getSubButtons().add(button33);

        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        return menu;
    }

}
