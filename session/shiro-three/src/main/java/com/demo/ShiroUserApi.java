package com.demo;

import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhangShaowei on 2021/7/26 11:11
 */
@RequestMapping
@RestController
public class ShiroUserApi {

    @GetMapping("hello")
    private String hello() {
        return "Hello World!";
    }

    @GetMapping("user")
    public Object user() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getPrincipal();
    }

    @SneakyThrows
    @GetMapping("login")
    public String login(
            HttpServletResponse response,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(usernamePasswordToken);
        response.sendRedirect("/user");
        return "success";
    }

    @SneakyThrows
    @GetMapping("logout")
    public String logout() {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "success";
    }

}
