package com.zsw;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/6/4 15:36
 **/
@RequestMapping("nacos")
@RestController
public class NacosController {


    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @NacosInjected
    NamingService namingService;


    @GetMapping("get")
    public boolean get() {
        return this.useLocalCache;
    }


    @SneakyThrows
    @GetMapping("name")
    public Object name(@RequestParam("name") String name) {
        return this.namingService.getAllInstances(name);
    }

}
