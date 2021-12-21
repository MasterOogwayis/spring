package com.spring.security;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程下 {@link SecurityContextHolder#getContext()} 会获取失败
 * 有一下 3 种方式解决
 * 1. 使用 security executor {@link DelegatingSecurityContextExecutor} {@link DelegatingSecurityContextExecutorService}
 * 2. 使用 security runnable {@link DelegatingSecurityContextRunnable}
 * 3. 通过系统参数设置 SecurityContextHolder
 * {@link SecurityContextHolder#SYSTEM_PROPERTY} 策略为 {@link SecurityContextHolder#MODE_INHERITABLETHREADLOCAL}
 * <p>
 * 其他所有并发类 DelegatingSecurityContext***
 *
 * @author ZhangShaowei on 2021/12/21 10:02
 */
@Slf4j
public class AsyncExecutorTests {


    /**
     * @see DelegatingSecurityContextExecutor
     */
    @SneakyThrows
    @Test
    public void test1() {
        SecurityContext context = SecurityContextHolder.getContext();

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                "root", "doesnotmatter", AuthorityUtils.createAuthorityList("admin"));
        context.setAuthentication(authenticationToken);

        Executor executor = new SimpleAsyncTaskExecutor();
        DelegatingSecurityContextExecutor securityContextExecutor = new DelegatingSecurityContextExecutor(executor);

        securityContextExecutor.execute(() -> {
            log.info("user: {}", SecurityContextHolder.getContext().getAuthentication());
        });

        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * @see DelegatingSecurityContextRunnable
     */
    @SneakyThrows
    @Test
    public void test2() {
        SecurityContext context = SecurityContextHolder.getContext();

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                "root", "doesnotmatter", AuthorityUtils.createAuthorityList("admin"));
        context.setAuthentication(authenticationToken);

        Executor executor = new SimpleAsyncTaskExecutor();

        executor.execute(new DelegatingSecurityContextRunnable(() -> {
            log.info("user: {}", SecurityContextHolder.getContext().getAuthentication());
        }, context));

        TimeUnit.SECONDS.sleep(1);
    }


    @SneakyThrows
    @Test
    public void test3() {
        System.setProperty(SecurityContextHolder.SYSTEM_PROPERTY, SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        SecurityContext context = SecurityContextHolder.getContext();

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                "root", "doesnotmatter", AuthorityUtils.createAuthorityList("admin"));
        context.setAuthentication(authenticationToken);

        Executor executor = new SimpleAsyncTaskExecutor();
        executor.execute(() -> {
            log.info("user: {}", SecurityContextHolder.getContext().getAuthentication());
        });

        TimeUnit.SECONDS.sleep(1);
    }

}
