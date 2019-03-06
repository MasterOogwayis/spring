package com.zsw.base.aspect;

import com.zsw.base.cache.annotation.CacheLock;
import com.zsw.base.redis.dao.commons.BaseCacheDao;
import com.zsw.base.utils.KeyGeneratorUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2017/10/12 16:13
 */
@Aspect
@Order(1)
@Component
public class CacheLockAspect {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     */
    @Autowired
    private BaseCacheDao cache;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.zsw.base.cache.annotation.CacheLock)")
    public void methods() {
    }

    @Before("methods()")
    public void before() {

    }


    /**
     * @param joinPoint ProceedingJoinPoint
     * @return method.invoke()
     * @throws Throwable
     */
    @Around("methods()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        CacheLock cacheLock = targetMethod.getAnnotation(CacheLock.class);
        String key = KeyGeneratorUtils.parseKey(cacheLock.key(), targetMethod, joinPoint.getArgs());
        this.logger.info("AOP:" + key);
        System.err.println(cacheLock.key());

        Object data = joinPoint.proceed(joinPoint.getArgs());
        return data;

    }

    /**
     * @param joinPoint
     */
    @After("methods()")
    public void after(JoinPoint joinPoint) {

    }


    private boolean lock(final String key, final long timeout, final long expire) {
        long millTime = System.currentTimeMillis();
        boolean locked = false;
        try {
            //在timeout的时间范围内不断轮询锁
            while (System.currentTimeMillis() - millTime < timeout) {
                //锁不存在的话，设置锁并设置锁过期时间，即加锁
                if (this.cache.setNXExpire(key, "locked", expire, TimeUnit.MILLISECONDS)) {
                    //锁的情况下锁过期后消失，不会造成永久阻塞
                    locked = true;
                    break;
                }
                //短暂休眠，避免可能的活锁
                Thread.sleep(5, RandomUtils.nextInt(10));
            }
        } catch (Exception e) {
            throw new RuntimeException("locking error", e);
        }
        return locked;
    }

    /**
     * @param key
     */
    private void unlock(final String key) {
        this.cache.del(key);
    }

}
