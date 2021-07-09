//package com.zsw.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.session.Session;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author ZhangShaowei on 2021/7/9 9:46
// */
//public class CustomHttpSessionStrategy implements HttpSessionStrategy {
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Override
//    public String getRequestedSessionId(HttpServletRequest request) {
//        String sessionId = request.getSession().getId();
//        String jsession = this.redisTemplate.opsForValue().get(sessionId);
//
//
//        return null;
//    }
//
//    @Override
//    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
//
//    }
//
//    @Override
//    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
//
//    }
//}
