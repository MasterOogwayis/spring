package com.zsw.orm.client.rule;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;

import java.util.List;
import java.util.Objects;

/**
 * 自定义 负载策略，用于访问本机客户端
 * server-name:
 * ribbon:
 * NFLoadBalancerRuleClassName: com.zsw.orm.client.rule.CustomRibbonLoadBalanceRule
 *
 * @author ZhangShaowei on 2018/12/26 10:22
 **/
@Slf4j
public class CustomRibbonLoadBalanceRule implements IRule {

//    private InetUtilsProperties properties;

    /**
     *
     */
    private ILoadBalancer balancer = new BaseLoadBalancer();

    private String localIpAddress = null;

    private InetUtils inetUtils;

    public CustomRibbonLoadBalanceRule(InetUtilsProperties properties) {
//        this.properties = properties;
        this.inetUtils = new InetUtils(properties);
    }

    @Override
    public Server choose(Object key) {
        List<Server> allServers = balancer.getAllServers();
        try {
            if (Objects.isNull(localIpAddress)) {
                localIpAddress = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
            }
        } catch (Exception e) {
            log.info("未找到本机ip：", e);
            e.printStackTrace();
        }

        for (Server server : allServers) {
            if (server.getHost().equalsIgnoreCase(localIpAddress)) {
                log.info("本次Feign调用地址：host - [{}] Server - [{}]", server.getHost(), server.toString());
                return server;
            }
        }
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.balancer = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.balancer;
    }
}
