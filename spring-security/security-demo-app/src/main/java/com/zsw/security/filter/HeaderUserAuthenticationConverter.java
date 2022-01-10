package com.zsw.security.filter;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * @author ZhangShaowei on 2021/12/15 12:38
 */
public class HeaderUserAuthenticationConverter implements AuthenticationConverter {

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;

    private static final String AUTHENTICATION_SCHEME_ACCESS = "Access";

    private static final String HEADER_USER = "BBX-User";

    public HeaderUserAuthenticationConverter() {
        this.authenticationDetailsSource = new WebAuthenticationDetailsSource();
    }

    @Override
    public HeaderAuthenticationToken convert(HttpServletRequest request) {
        String header = request.getHeader(HEADER_USER);
        if (header == null) {
            return null;
        }

        header = header.trim();
//        if (!StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_ACCESS)) {
//            return null;
//        }

//        if (header.equalsIgnoreCase(AUTHENTICATION_SCHEME_ACCESS)) {
//            throw new BadCredentialsException("Empty Username authentication token");
//        }

        byte[] base64Token = header.getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }
        String token = new String(decoded, StandardCharsets.UTF_8);

        HeaderAuthenticationToken result = new HeaderAuthenticationToken(token);
        result.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return result;
    }
}
