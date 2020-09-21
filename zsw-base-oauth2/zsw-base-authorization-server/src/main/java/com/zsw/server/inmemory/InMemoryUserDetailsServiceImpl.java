package com.zsw.server.inmemory;

import com.zsw.base.oauth2.user.AbstractClientUserDetailsService;
import com.zsw.base.oauth2.user.ClientUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2020/9/11 10:41
 */
@Component
@AllArgsConstructor
public class InMemoryUserDetailsServiceImpl extends AbstractClientUserDetailsService {

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username, String clientId) {
        ClientUser user = new ClientUser(
                "root",
                passwordEncoder.encode("root"),
                true,
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"))
        );

        Map<String, Object> attr = new LinkedHashMap<>();
        attr.put("age", 18);
        attr.put("address", "somewhere in space.");
        user.setAttr(attr);
        return user;
    }


    @Override
    public boolean support(String clientId, String username) {
        return Objects.equals("postman-inmemory", clientId);
    }
}
