package com.zsw.server.cms;

import com.zsw.pojo.ApiResponse;
import com.zsw.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：用户管理
 *
 * @author ZhangShaowei
 * @version 2.3.0
 * @since 2.0.0
 */
@FeignClient(value = "${zsw.base.application.servername.cms}", fallback = CmsUserClientHystrix.class)
public interface CmsUserClient {

    /**
     * 通过用户名获取用户
     *
     * @param username username
     * @return 用户
     */
    @GetMapping("/user/get")
    ApiResponse<UserDto> getByUsername(@RequestParam("username") String username);

    @GetMapping("/user/logout")
    ApiResponse<String> logout();

}
