package com.zsw.base.oauth2.support;

import com.zsw.base.oauth2.resource.ResourceUserDetailsService;
import com.zsw.base.oauth2.user.ClientUser;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.zsw.base.oauth2.resource.ResourceUserDetailsService.LOGIN_TIMESTAMP;


/**
 * @author ZhangShaowei on 2020/9/16 15:31
 */
@AllArgsConstructor
public class AdditionalAccessTokenConverter extends JwtAccessTokenConverter {

    private final ResourceUserDetailsService resourceUserDetailsService;

    /**
     * token 附加信息
     * 用户附加信息存缓存
     *
     * @param accessToken    DefaultOAuth2AccessToken
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
            long timestamp = System.currentTimeMillis();
            attr.put(LOGIN_TIMESTAMP, timestamp);

            // 将时间戳编码进 token，用于白名单模式
            ((DefaultOAuth2AccessToken) accessToken)
                    .setAdditionalInformation(Collections.singletonMap(LOGIN_TIMESTAMP, timestamp));
            if (Objects.nonNull(resourceUserDetailsService)) {
                // 保存额外信息，以便调用链上面使用
                resourceUserDetailsService.save(
                        authentication.getName(),
                        authentication.getOAuth2Request().getClientId(),
                        attr, accessToken.getExpiresIn()
                );
            }
            OAuth2AccessToken token = super.enhance(accessToken, authentication);
            // 编码后消除时间戳明文
            token.getAdditionalInformation().remove(LOGIN_TIMESTAMP);
            // 附加的额外参数
            if (!CollectionUtils.isEmpty(user.getExtra())) {
                token.getAdditionalInformation().putAll(user.getExtra());
            }
            return token;
        }
        return super.enhance(accessToken, authentication);
    }
}
