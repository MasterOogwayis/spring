package com.zsw.base.oauth2;

import com.zsw.pojo.ApiResponse;
import com.zsw.pojo.user.SessionUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author ZhangShaowei on 2020/9/17 16:22
 */
@RequestMapping("/user/logout")
@AllArgsConstructor
public class LogoutEndpoint {

    private final SessionUserService sessionUserService;

    private final ClientUserDetailsService clientUserDetailsService;

    @GetMapping
    @ResponseBody
    public ApiResponse<?> logout() {
        Optional.ofNullable(this.sessionUserService)
                .map(SessionUserService::getUserInfo)
                .ifPresent(userInfo -> {
                    LogoutEndpoint.this.clientUserDetailsService.logout(userInfo.getUsername(), userInfo.getClientId());
                });
        return ApiResponse.success();
    }
}
