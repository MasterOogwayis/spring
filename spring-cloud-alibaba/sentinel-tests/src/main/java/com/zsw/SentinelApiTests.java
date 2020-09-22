package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//        initFlowRules();
        SpringApplication.run(SentinelApiTests.class, args);
    }


//    @Bean
//    public SentinelResourceAspect sentinelResourceAspect() {
//        return new SentinelResourceAspect();
//    }

//    private static void initFlowRules() {
//        List<FlowRule> rules = new ArrayList<>();
//        FlowRule rule = new FlowRule();
//        rule.setResource("hello");
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        // Set limit QPS to 20.
//        rule.setCount(10);
//        rules.add(rule);
//        FlowRuleManager.loadRules(rules);
//    }

}
