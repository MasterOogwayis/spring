package com.zsw.base.oauth2.userinfo.impl;

import com.zsw.base.oauth2.support.ClientUserDetails;
import com.zsw.user.SessionUserService;
import com.zsw.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * oauth2 impl
 *
 * @author ZhangShaowei on 2020/9/10 14:11
 */
@Slf4j
public class ResourceSessionUserServiceImpl implements SessionUserService {
    @Override
    public UserInfo getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(oAuth2Authentication.getName());
            Collection<GrantedAuthority> collection = oAuth2Authentication.getAuthorities();
            userInfo.setRoles(collection.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
            Object principal = oAuth2Authentication.getPrincipal();
            if (principal instanceof ClientUserDetails) {
                ClientUserDetails clientUserDetails = (ClientUserDetails) principal;
                userInfo.setSupplier(clientUserDetails::getAttr);
                userInfo.setClientId(clientUserDetails.getClientId());
            }
            return userInfo;
        }
        log.error("获取登录用户失败！");
        return null;
    }
}
