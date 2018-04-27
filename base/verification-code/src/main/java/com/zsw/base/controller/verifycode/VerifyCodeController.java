package com.zsw.base.controller.verifycode;

import com.zsw.base.utils.VerifyCode;
import com.zsw.base.verifycode.Captcha;
import com.zsw.base.verifycode.GifCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码图片 Verification code
 *
 * @author ZhangShaowei on 2018/3/21 10:47
 **/
@RestController
@RequestMapping("verify")
public class VerifyCodeController {


    /**
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException e
     */
    @PostMapping("code")
    public void code(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //设置输出图片
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // gif验证码, 宽、高、位数
        Captcha captcha = new GifCaptcha(130, 38, 5);
        // 存入servletContext
        request.getSession().setAttribute(VerifyCode.SESSION_KEY, captcha.text().toLowerCase());

        try (OutputStream out = response.getOutputStream()){
            //输出图片
            captcha.out(out);
        }

    }

    /**
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException e
     */
    @GetMapping("code")
    public void codeGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        code(request, response);
    }


}
