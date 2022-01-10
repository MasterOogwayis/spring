package com.zsw;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/12/21 15:12
 */
@Slf4j
@AllArgsConstructor
@RestController
public class SecurityApi {

    private final ThreadPoolTaskExecutor executor;


    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @GetMapping("user")
    public Object user() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("sum")
    public Object set(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        return a + b;
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("helloWorld")
    public Object helloWorld() {
        return "Hello World!";
    }


    @GetMapping("execute")
    public Object execute(@RequestParam("num") Integer num) {
        for (int i = 0; i < num; i++) {
            this.executor.execute(new DelegatingSecurityContextRunnable(() -> {
                log.info("current user: {}", SecurityContextHolder.getContext().getAuthentication());
            }));
        }
        this.executor.execute(() -> {
            log.info("current user: {}", SecurityContextHolder.getContext().getAuthentication());
        });
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
