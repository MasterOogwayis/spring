package com.zsw.conf.security;

import com.zsw.base.utils.VerifyCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginAuthenticationFilter 验证码 Filter
 *
 * @author ZhangShaowei on 2018/4/26 14:38
 **/
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    protected LoginAuthenticationFilter() {
        // 只拦截 登录 POST login
        super(new AntPathRequestMatcher("/login", "POST"));
        // 失败后跳转地址
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
    }

    @Override
    public void doFilter(
            ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!requiresAuthentication(request, response)) {
            chain.doFilter(request, response);

            return;
        }
        String code = obtainVerifyCode(request);
        String verfyCode = request.getSession().getAttribute(VerifyCode.SESSION_KEY).toString();
        if (!verfyCode.equalsIgnoreCase(code)) {
            unsuccessfulAuthentication(request, response, new BadCredentialsException("验证码错误"));
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        return null;
    }


    /**
     * Enables subclasses to override the composition of the username, such as by
     * including additional values and a separator.
     *
     * @param request so that request attributes can be retrieved
     *
     * @return the username that will be presented in the <code>Authentication</code>
     * request token to the <code>AuthenticationManager</code>
     */
    protected String obtainVerifyCode(HttpServletRequest request) {
        return request.getParameter("code");
    }
}
