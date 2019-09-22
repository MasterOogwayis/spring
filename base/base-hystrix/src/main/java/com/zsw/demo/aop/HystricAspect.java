package com.zsw.demo.aop;

import com.zsw.demo.annotation.Limited;
import com.zsw.demo.annotation.Timeout;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Administrator on 2019/9/22 17:02
 **/
@Slf4j
@Aspect
@Component
public class HystricAspect {

    private Map<Method, Semaphore> semaphoreMap = new ConcurrentHashMap<>();

    private ExecutorService executorService = new ThreadPoolExecutor(8, 64,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());


    @Around("@annotation(com.zsw.demo.annotation.Timeout)")
    public Object timeout(ProceedingJoinPoint pjp) throws Throwable {
        Object returnValue = null;
        Signature signature = pjp.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            Timeout timeout = method.getAnnotation(Timeout.class);

            Object[] arguments = pjp.getArgs();

            Future<Object> future = this.executorService.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    try {
                        return pjp.proceed(arguments);
                    } catch (Throwable throwable) {
                        throw new Exception(throwable);
                    }
                }
            });

            try {
                returnValue = future.get(timeout.value(), timeout.timeUnit());
            } catch (TimeoutException e) {
                log.warn("调用超时，invoke fallback method, fallback={}", timeout.fallback());
                returnValue = invokeFallback(method, pjp.getTarget(), timeout.fallback(), arguments);
            }

        }
        return returnValue;
    }

    @Around("@annotation(com.zsw.demo.annotation.Limited)")
    public Object limited(ProceedingJoinPoint pjp) throws Throwable {
        Object returnValue = null;
        Signature signature = pjp.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            Semaphore semaphore = this.getSemaphore(method);
            try {
                semaphore.acquire();
                returnValue = pjp.proceed();
            } finally {
                semaphore.release();
            }
        }
        return returnValue;
    }



    private Object invokeFallback(Method method, Object target, String fallback, Object[] arguments) throws Exception {
        Method fallbackMethod = this.find(method, target, fallback);
        return fallbackMethod.invoke(target, arguments);
    }

    private Method find(Method method, Object bean, String name) throws NoSuchMethodException {
        Class<?> clazz = bean.getClass();
        return clazz.getMethod(name, method.getParameterTypes());
    }

    private Semaphore getSemaphore(Method method) {
        return this.semaphoreMap.computeIfAbsent(method, m -> {
            Limited limited = m.getAnnotation(Limited.class);
            return new Semaphore(limited.value());
        });
    }



}
