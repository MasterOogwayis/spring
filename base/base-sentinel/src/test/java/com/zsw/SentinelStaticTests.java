package com.zsw;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Shaowei Zhang on 2020/9/20 21:39
 */
public class SentinelStaticTests {

    public static void main(String[] args) {

//        ExecutorService executorService = new ThreadPoolExecutor(2, 16,
//                60, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>());


        initFlowRules();
        while (true) {
            try (Entry entry = SphU.entry("HelloWorld")) {
                // boolean 的方式
//                SphO.entry("");
                /*您的业务逻辑 - 开始*/
                System.out.println("hello world");
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(200L));
                /*您的业务逻辑 - 结束*/
            } catch (BlockException | InterruptedException e1) {
                /*流控逻辑处理 - 开始*/
                System.out.println("block!");
                /*流控逻辑处理 - 结束*/
            }
        }
    }


    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
