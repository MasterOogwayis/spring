package com.zsw;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ZhangShaowei on 2020/9/18 11:08
 */
@Slf4j
public class SentinelTests {

    public static void main(String[] args) {
        System.out.println(123);
    }


//    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
//            Runtime.getRuntime().availableProcessors() * 2 + 1,
//            50,
//            60,
//            TimeUnit.SECONDS,
//            new LinkedBlockingQueue<>(),
//            Executors.defaultThreadFactory(),
//            new ThreadPoolExecutor.CallerRunsPolicy());

    @Before
    public void init() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 15
        rule.setCount(150);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    @Test
    public void test() {
        int success = 0;
        int block = 0;
        int count = 0;
        while ((++ count) < 1000) {
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
//                log.debug("start ...");
//                System.out.println("Hello World");
                Thread.sleep(5);
//                log.debug("end ...");
                success++;
            } catch (BlockException e) {
//                log.debug("流控逻辑处理 - 开始");
//                e.printStackTrace();
//                log.debug("流控逻辑处理 - 结束");
                block ++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Optional.ofNullable(entry).ifPresent(Entry::close);
            }
        }
        log.debug("success = {}, block = {}", success, block);
    }



}
