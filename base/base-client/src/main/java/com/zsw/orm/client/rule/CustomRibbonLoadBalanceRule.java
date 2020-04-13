package com.zsw.orm.client.rule;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

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

    /**
     *
     */
    private ILoadBalancer balancer = new BaseLoadBalancer();

    private String localIpAddress = null;

    @Override
    public Server choose(Object key) {
        List<Server> allServers = balancer.getAllServers();
        try {
            if (Objects.isNull(localIpAddress)) {
                localIpAddress = InetUtils.getFirstNonLoopbackHostInfo().getIpAddress();
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
