package com.zsw.orm.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author ZhangShaowei on 2017/9/26 10:40
 */

public final class WebUtils {

    /**
     * Util
     */
    private WebUtils() {
    }

    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes.class::cast).map(ServletRequestAttributes::getRequest).orElse(null);
    }


    /**
     * 获取session
     *
     * @return HttpSession
     */
    public static HttpSession getHttpSession() {
        return Optional.ofNullable(getRequest()).map(HttpServletRequest::getSession).orElse(null);
    }

    /**
     * 获取ServletContext
     *
     * @return ServletContext
     */
    public static ServletContext getServletContext() {
        return Optional.ofNullable(getHttpSession()).map(HttpSession::getServletContext).orElse(null);
    }

    /**
     * 顺序的从Request，Session，ServletContext里获取属性值
     *
     * @param name name
     * @return Object
     */
    public static Object getAttribute(final String name) {
        return
                Optional.ofNullable(getRequest()).map(request -> request.getAttribute(name)).orElse(
                        Optional.ofNullable(getHttpSession()).map(session -> session.getAttribute(name)).orElse(
                                Optional.of(getServletContext()).map(context -> context.getAttribute(name)).orElse(null)
                        )
                );
    }

}
