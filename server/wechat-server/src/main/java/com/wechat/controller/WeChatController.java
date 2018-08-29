package com.wechat.controller;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author ZhangShaowei on 2018/8/29 9:38
 **/
@RestController
@RequestMapping("/wechat/portal")
public class WeChatController {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 公众号服务
     */
    @Autowired
    private WxMpService wxService;

    /**
     * 消息处理器
     */
    @Autowired
    private WxMpMessageRouter router;


    @GetMapping
    public String authGet(
            @RequestParam(name = "signature") String signature,
            @RequestParam(name = "timestamp") String timestamp,
            @RequestParam(name = "nonce") String nonce,
            @RequestParam(name = "echostr") String echostr) {
        this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (this.wxService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }
        return "非法请求";
    }

    @PostMapping
    public String post(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(name = "encrypt_type", required = false) String encType,
            @RequestParam(name = "msg_signature", required = false) String msgSignature) throws IllegalAccessException {
        this.logger.debug("\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);
        if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
            this.logger.warn("接收到无法解析的请求，可能属于伪造的请求！");
            throw new IllegalAccessException("非法请求，可能属于伪造的请求！");
        }

        String out = "";
        if (StringUtils.isBlank(encType)) {
            // 明文传输
            WxMpXmlMessage message = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(message);
            if (Objects.nonNull(outMessage)) {
                out = outMessage.toXml();
            }
        } else if ("aes".equals(encType)){
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                    requestBody, this.wxService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (Objects.nonNull(outMessage)) {
                out = outMessage.toEncryptedXml(this.wxService.getWxMpConfigStorage());
            }
        }
        this.logger.debug("\n组装回复消息: {}", out);
        return out;
    }


    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {
            return this.router.route(message);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        return null;
    }

}
