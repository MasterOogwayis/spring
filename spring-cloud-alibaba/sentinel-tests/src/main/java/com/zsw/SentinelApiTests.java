package com.zsw;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2020/9/18 11:04
 */
@SpringBootApplication
public class SentinelApiTests {




    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        System.setProperty("csp.sentinel.dashboard.server", "localhost:18080");
        System.setProperty("project.name", "SentinelTests");
        initFlowRules();
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
