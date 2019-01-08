package com.wechat.controller;

import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/1/4 13:58
 **/
@RestController
@RequestMapping("wechat/pay")
public class WxPayController {

    WxPayService wxPayService;


}
