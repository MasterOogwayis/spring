package com.zsw.promethues.aspect;

/**
 * PrometheusMetricsAspect
 *
 * @author ZhangShaowei on 2018/3/29 15:27
 **/
//@Aspect
//@Component
public class PrometheusMetricsAspect {

//    private static final Counter requestTotal = Counter.build().name("couter_all").labelNames("api").help("total request couter of api").register();
//    private static final Counter requestError = Counter.build().name("couter_error").labelNames("api").help("response Error couter of api").register();
//    private static final Histogram histogram = Histogram.build().name("histogram_consuming").labelNames("api").help("response consuming of api").register();
//
//    @Pointcut("@annotation(com.zsw.promethues.annotation.PrometheusMetrics)")
//    public void pointcut() {
//    }
//
//    @Around(value = "pointcut() && @annotation(annotation)")
//    public Object around(ProceedingJoinPoint joinPoint, PrometheusMetrics annotation) throws Throwable {
//
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        PrometheusMetrics prometheusMetrics = methodSignature.getMethod().getAnnotation(PrometheusMetrics.class);
//        if (prometheusMetrics != null) {
//            String name;
//            if (StringUtils.isNotEmpty(prometheusMetrics.name())) {
//                name = prometheusMetrics.name();
//            } else {
//                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                        .getRequest();
//                name = request.getRequestURI();
//            }
//
//
//            Histogram.Timer requestTimer = histogram.labels(name).startTimer();
//            Object object;
//            try {
//                object = joinPoint.proceed();
//            } catch (Exception e) {
//                requestError.labels(name).inc();
//                throw e;
//            } finally {
//                requestTimer.observeDuration();
//            }
//
//            return object;
//        } else {
//            return joinPoint.proceed();
//        }
//
//    }

}
