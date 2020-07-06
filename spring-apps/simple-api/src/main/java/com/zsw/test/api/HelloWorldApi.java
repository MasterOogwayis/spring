package com.zsw.test.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author ZhangShaowei on 2020/5/11 14:18
 */
@Slf4j
@RestController
public class HelloWorldApi {

    @Autowired
    private TestService testService;

    @SneakyThrows
    @GetMapping("hello")
    public Object hello(@RequestParam("name") String name) {
        MethodHandles.Lookup lookup = BeanUtils.instantiateClass(
                MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class), TestService.class, 15);
        MethodHandle hello = lookup.findVirtual(TestService.class, "hello", MethodType.methodType(String.class, String.class));
        Object invoke = hello.bindTo(this.testService).invoke(name);

        Method method = TestService.class.getDeclaredMethod("hello", String.class);
        Object invoke1 = method.invoke(this.testService, name);
        System.err.println(invoke);
        System.out.println(invoke1);

        return invoke;
    }


    @PostMapping("test")
    public Object test(@RequestBody Map<String, Object> body) {
        return body;
    }


}
