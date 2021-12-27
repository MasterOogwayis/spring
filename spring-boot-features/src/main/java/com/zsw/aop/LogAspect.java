//package com.zsw.aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * @author ZhangShaowei on 2021/6/30 16:39
// */
//@Slf4j
//@Aspect
//@Component
//@Order(1)
//public class LogAspect {
//
//    @Pointcut("@annotation(com.zsw.annotation.Log)")
//    public void pointcut() {
//        // Do nothing
//    }
//
//
//    @Around(value = "pointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//
//        logRequest(joinPoint);
//        Object response = joinPoint.proceed();
//        logResponse(startTime, response);
//        return response;
//
//    }
//
//    private void logResponse(long startTime, Object response) {
//
//        try {
//            long endTime = System.currentTimeMillis();
//            log.debug("response : {}", response);
//            log.debug("cost : {}ms", (endTime - startTime));
//        } catch (Exception e) {
//            //swallow it
//            log.error("logResponse error", e);
//        }
//
//    }
//
//    private void logRequest(ProceedingJoinPoint joinPoint) {
//        try {
//            log.debug("start processing: {}", joinPoint.getSignature().toShortString());
//            Object[] args = joinPoint.getArgs();
//            for (Object arg : args) {
//                log.debug("request : {}", arg);
//            }
//        } catch (Exception e) {
//            log.error("logRequest error", e);
//        }
//
//    }
//
//
//}
