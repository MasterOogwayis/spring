package com.zsw.security.filter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author
 *
 * @author ZhangShaowei on 2021/12/15 11:16
 */
public class HeaderUserAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    private final HeaderUserAuthenticationConverter authenticationConverter = new HeaderUserAuthenticationConverter();

    public HeaderUserAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final boolean debug = this.logger.isDebugEnabled();
        try {
            HeaderAuthenticationToken authRequest = authenticationConverter.convert(request);
            if (authRequest == null) {
                chain.doFilter(request, response);
                return;
            }

            String username = authRequest.getName();

            if (debug) {
                this.logger
                        .debug("'BBX-User' Authorization header found for user '"
                                + username + "'");
            }

            if (authenticationIsRequired(username)) {
                Authentication authResult = this.authenticationManager.authenticate(authRequest);

                if (debug) {
                    this.logger.debug("Authentication success: " + authResult);
                }

                SecurityContextHolder.getContext().setAuthentication(authResult);

            }

        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();

            if (debug) {
                this.logger.debug("Authentication request for failed!", failed);
            }

            throw new AccessDeniedException(failed.getMessage());

        }

        chain.doFilter(request, response);
    }


    private boolean authenticationIsRequired(String username) {
        Authentication existingAuth = SecurityContextHolder.getContext()
                .getAuthentication();

        if (existingAuth == null || !existingAuth.isAuthenticated()) {
            return true;
        }

        if (existingAuth instanceof UsernamePasswordAuthenticationToken
                && !existingAuth.getName().equals(username)) {
            return true;
        }

        if (existingAuth instanceof AnonymousAuthenticationToken) {
            return true;
        }

        return false;
    }


}
