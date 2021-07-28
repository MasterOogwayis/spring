package com.demo.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/7/26 12:23
 */
public class LoginFilter extends UserFilter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        Map<String, String> map = new HashMap<>();
        map.put("error", "unauthorized");
        map.put("error_description", "Full authentication is required to access this resource");
        if (response instanceof HttpServletResponse){
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        response.getWriter().write(OBJECT_MAPPER.writeValueAsString(map));
    }

}
