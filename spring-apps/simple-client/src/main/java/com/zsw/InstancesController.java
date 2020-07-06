package com.zsw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/12 9:45
 **/
@RequestMapping("services")
@RestController
public class InstancesController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping
    public Object services() {
        return this.discoveryClient.getServices();
    }

    @GetMapping("/{serviceName}")
    public Object serviceName(@PathVariable String serviceName) {
        return this.discoveryClient.getInstances(serviceName);
    }

    @GetMapping("/{serviceName}/{instanceId}")
    public Object getServiceInstance(@PathVariable String serviceName, @PathVariable String instanceId) {
        return this.discoveryClient.getInstances(serviceName)
                .stream()
                .filter(serviceInstance -> instanceId.equalsIgnoreCase(serviceInstance.getInstanceId()))
                .findFirst().orElseThrow(() -> new RuntimeException("No Such service instance"));
    }


}
