package com.zsw;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.csp.sentinel.transport.config.TransportConfig.CONSOLE_SERVER;

/**
 * @author ZhangShaowei on 2020/9/18 11:04
 */
@SpringBootApplication
public class SentinelApiTests {


    /**
     * start server
     * <p>
     * dashboard ->
     * -Xms128m
     * -Xmx256m
     * -Dserver.port=18080
     * -Dcsp.sentinel.dashboard.server=localhost:18080
     * -Dproject.name=sentinel-dashboard
     * <p>
     * agent ->
     * -Dcsp.sentinel.dashboard.server=localhost:18080
     * -Dproject.name=SentinelTests
     *
     * @param args args
     */
    public static void main(String[] args) {
//        System.setProperty("csp.sentinel.dashboard.server", "localhost:18080");
//        System.setProperty("project.name", "SentinelTests");
        // 不要打开这个 连不上服务器
        // 会导致 SentinelConfig#loadProps 提前调用读取不到 xx.yml 配置
//        initFlowRules();
//        SentinelConfig.setConfig(CONSOLE_SERVER, "localhost:8080");
        SpringApplication.run(SentinelApiTests.class, args);
    }


    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("hello");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(10);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
