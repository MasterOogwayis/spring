package com.zsw.temp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author ZhangShaowei on 2020/12/16 11:43
 */
@Slf4j
@Controller
public class WeixinController {

    private static final String appId = "*";
    private static final String key = "*";
    private static final String token = "*";


    @ResponseBody
    @RequestMapping(value = "/weixin/weixin", method = RequestMethod.POST)
    public String weixin(HttpServletRequest request) throws Exception {
        // xml请求解析
        Map<String, String> requestMap = MessageUtil.parseEncryptXml(
                request, token, key, appId);//密文模式
        log.debug("interface weixin:{}", requestMap);
        //Map<String, String> requestMap = MessageUtil.parseXml(request);//明文模式
        //加密回送xml数据包
        return MessageUtil.encryptXmlMessage("success", token, key, appId);
    }


    @GetMapping("/weixin/weixin")
    public String get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("get method: {}", request.getParameterMap().toString());
        return "get method";
    }

}
