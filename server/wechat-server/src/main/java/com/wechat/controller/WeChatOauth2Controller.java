package com.wechat.controller;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权跳转 获取用户信息
 *
 * @author ZhangShaowei on 2018/8/29 14:14
 **/
@RestController
@RequestMapping("/oauth2/*")
public class WeChatOauth2Controller {

    @Autowired
    private WxMpService wxMpService;


    @GetMapping
    public void oauth(
            HttpServletResponse response,
            @RequestParam String code, @RequestParam(required = false) String state) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.getWriter().println("<h1>code</h1>");
            response.getWriter().println(code);

            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = this.wxMpService.oauth2getAccessToken(code);
            response.getWriter().println("<h1>access token</h1>");
            response.getWriter().println(wxMpOAuth2AccessToken.toString());

            WxMpUser wxMpUser = this.wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            response.getWriter().println("<h1>user info</h1>");
            response.getWriter().println(wxMpUser.toString());

            wxMpOAuth2AccessToken = this.wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
            response.getWriter().println("<h1>after refresh</h1>");
            response.getWriter().println(wxMpOAuth2AccessToken.toString());

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        response.getWriter().flush();
        response.getWriter().close();
    }

}
