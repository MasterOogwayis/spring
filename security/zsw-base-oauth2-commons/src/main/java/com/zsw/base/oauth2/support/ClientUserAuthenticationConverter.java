package com.zsw.base.oauth2.support;

import com.zsw.base.oauth2.resource.ResourceUserDetails;
import com.zsw.base.oauth2.resource.ResourceUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static com.zsw.base.oauth2.resource.ResourceUserDetailsService.LOGIN_TIMESTAMP;
import static org.springframework.security.oauth2.provider.token.AccessTokenConverter.CLIENT_ID;

/**
 * 实现不同 client 的 UserInfo
 *
 * @author ZhangShaowei on 2020/3/2 14:14
 * @see DefaultUserAuthenticationConverter
 */
public class ClientUserAuthenticationConverter implements UserAuthenticationConverter {

    private Collection<? extends GrantedAuthority> defaultAuthorities;

    private ResourceUserDetailsService resourceUserDetailsService;

    private Boolean validate = false;

    /**
     * Optional {@link UserDetailsService} to use when extracting an {@link Authentication} from the incoming map.
     *
     * @param resourceUserDetailsService the userDetailsService to set
     */
    public void setUserDetailsService(ResourceUserDetailsService resourceUserDetailsService) {
        this.resourceUserDetailsService = resourceUserDetailsService;
    }

    /**
     *
     */
    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    /**
     * Default value for authorities if an Authentication is being created and the input has no data for authorities.
     * Note that unless this property is set, the default Authentication created by {@link #extractAuthentication(Map)}
     * will be unauthenticated.
     *
     * @param defaultAuthorities the defaultAuthorities to set. Default null.
     */
    public void setDefaultAuthorities(String[] defaultAuthorities) {
        this.defaultAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                .arrayToCommaDelimitedString(defaultAuthorities));
    }

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            Object principal = map.get(USERNAME);
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
            if (resourceUserDetailsService != null) {
                String username = map.get(USERNAME).toString();
                String clientId = map.containsKey(CLIENT_ID) ? map.get(CLIENT_ID).toString() : null;
                ResourceUserDetails user = resourceUserDetailsService.loadUserByUsername(username, clientId);
                if (map.containsKey(LOGIN_TIMESTAMP)) {
                    // 如果token 里面附加了登录时间戳，判断是否验证当前 token 和附加信息 是否有效
                    // 这就是单点登录
                    if (this.valid(user, map)) {
                        throw new InvalidTokenException("Expired");
                    }
                    user.setLoginTimestamp(Long.valueOf(map.get(LOGIN_TIMESTAMP).toString()));
                }
                if (!CollectionUtils.isEmpty(user.getAuthorities())) {
                    authorities = user.getAuthorities();
                }
                principal = user;
            }
            return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
        }
        return null;
    }

    private boolean valid(ResourceUserDetails user, Map<String, ?> map) {
        if (this.validate) {
            Map<String, Object> attr = user.getAttr();
            return Objects.isNull(attr) || !Objects.equals(map.get(LOGIN_TIMESTAMP), attr.get(LOGIN_TIMESTAMP));
        }
        return Boolean.FALSE;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        if (!map.containsKey(AUTHORITIES)) {
            return defaultAuthorities;
        }
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }


}
