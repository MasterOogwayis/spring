package com.zsw.server.cms;

import com.zsw.pojo.ApiResponse;
import com.zsw.pojo.user.UserDto;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2020/9/14 15:24
 */
@Component
public class CmsUserClientHystrix implements CmsUserClient {
    @Override
    public ApiResponse<UserDto> getByUsername(String username) {
        return ApiResponse.fail();
    }

    @Override
    public ApiResponse<String> logout() {
        return ApiResponse.fail();
    }
}
