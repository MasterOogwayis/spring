package com.zsw.base.oauth2;

import com.zsw.base.oauth2.resource.ResourceUserDetailsService;
import com.zsw.pojo.ApiResponse;
import com.zsw.user.SessionUserService;
import com.zsw.user.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZhangShaowei on 2020/9/17 16:22
 */
@RequestMapping("/user/logout")
@AllArgsConstructor
public class LogoutEndpoint {

    private final SessionUserService sessionUserService;

    private final ResourceUserDetailsService resourceUserDetailsService;

    /**
     * 退出
     *
     * @return logout
     */
    @DeleteMapping("logout")
    public ApiResponse<?> logout() {
        UserInfo userInfo = sessionUserService.getUserInfo();
        this.resourceUserDetailsService.logout(userInfo.getUsername(), userInfo.getClientId());
        return ApiResponse.success();
    }
}
