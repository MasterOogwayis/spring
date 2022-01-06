package com.zsw;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2022/1/5 11:37
 */
@RequestMapping("hello")
@RestController
public class V3ConsumerApi {

    @DubboReference(
            interfaceName = "com.zsw.service.HelloService",
            version = "1.0.0"
    )
    private GenericService genericService;


    @GetMapping
    public Object hello(@RequestParam("name") String name) {
        return this.genericService.$invoke("hello", new String[]{String.class.getName()}, new Object[]{name});
    }

}
