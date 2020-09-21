package com.zsw;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2020/9/18 11:08
 */
@Slf4j
public class SentinelTests {

    public static void main(String[] args) {
        init(50);
        int success = 0;
        int block = 0;
        int count = 0;
        while ((++count) < 1000) {
            Entry entry = null;
            if (SphO.entry("HelloWorld")) {
//                log.debug("start ...");
//                System.out.println("Hello World");
//                log.debug("end ...");
                success++;

            } else {
//                log.debug("流控逻辑处理 - 开始");
//                e.printStackTrace();
//                log.debug("流控逻辑处理 - 结束");
                block++;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(30));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.debug("success = {}, block = {}", success, block);
    }

    public static void init(int qps) {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 15
        rule.setCount(qps);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }


}
