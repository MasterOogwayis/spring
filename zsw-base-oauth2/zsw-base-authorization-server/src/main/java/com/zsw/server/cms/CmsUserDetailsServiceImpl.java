package com.zsw.server.cms;

import com.zsw.base.oauth2.user.AbstractClientUserDetailsService;
import com.zsw.base.oauth2.user.ClientUser;
import com.zsw.pojo.ApiResponse;
import com.zsw.user.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2020/9/14 15:21
 */
@Service
@AllArgsConstructor
public class CmsUserDetailsServiceImpl extends AbstractClientUserDetailsService {
    private static final String CLIENT_ID = "backend-api";

    private final CmsUserClient cmsUserClient;

    @Override
    public UserDetails loadUserByUsername(String username, String clientId) throws UsernameNotFoundException {
        UserDto userDto = Optional.ofNullable(username)
                .map(this.cmsUserClient::getByUsername)
                .filter(ApiResponse::isSuccess)
                .map(ApiResponse::getData)
                .orElseThrow(() -> new BadCredentialsException("用户名或密码错误!"));
        ClientUser clientUser = new ClientUser(
                username,
                userDto.getPassword(),
                true,
                true,
                true,
                true,
                userDto.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
        Map<String, Object> attr = new HashMap<>();
        attr.put("status", userDto.getStatus());
        attr.put("name", "张三");
        clientUser.setAttr(attr);
        return clientUser;
    }

    @Override
    public boolean supports(String clientId, String username) {
        return CLIENT_ID.equals(clientId);
    }
}
