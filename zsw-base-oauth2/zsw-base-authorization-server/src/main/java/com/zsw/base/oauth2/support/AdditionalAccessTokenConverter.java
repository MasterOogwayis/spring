package com.zsw.base.oauth2.support;

import com.zsw.base.oauth2.ClientUserDetailsService;
import com.zsw.base.oauth2.user.ClientUser;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

import static com.zsw.base.oauth2.ClientUserDetailsService.LOGIN_TIMESTAMP;


/**
 * @author ZhangShaowei on 2020/9/16 15:31
 */
@AllArgsConstructor
public class AdditionalAccessTokenConverter extends JwtAccessTokenConverter {

    private final ClientUserDetailsService userDetailsService;

    /**
     * token 附加信息
     * 用户附加信息存缓存
     *
     * @param accessToken DefaultOAuth2AccessToken
     * @param authentication OAuth2Authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getPrincipal() instanceof ClientUser) {
            ClientUser user = (ClientUser) authentication.getPrincipal();
            Map<String, Object> attr = new HashMap<>();
            if (!CollectionUtils.isEmpty(user.getAttr())) {
                attr.putAll(user.getAttr());
            }
            attr.put(LOGIN_TIMESTAMP, System.currentTimeMillis());
            String clientId = authentication.getOAuth2Request().getClientId();
            String username = authentication.getName();
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(attr);
            this.userDetailsService.save(username, clientId, attr, accessToken.getExpiresIn());
        }
        return super.enhance(accessToken, authentication);
    }
}
