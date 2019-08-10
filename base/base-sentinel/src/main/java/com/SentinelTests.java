package com;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * -Dcsp.sentinel.dashboard.server=localhost:8888 -Dproject.name=demo
 *
 * @author Administrator on 2019/8/8 20:51
 **/
@Slf4j
public class SentinelTests {

    private static final String SOURCE = "sayHello";


    public static void main(String[] args) {
        initSentinel();
        Executor executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() * 2 + 1,
                200,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {

                while (true) {
                    Entry entry = null;
                    try {
                        entry = SphU.entry(SOURCE);
                        log.info("Success Passed,{}", SOURCE);
                    } catch (BlockException e) {
                        log.warn("Blocking, {}", e.getMessage());
                    } finally {
                        if (Objects.nonNull(entry)) {
                            entry.exit();
                        }
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100, 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }

    private static void initSentinel() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(SOURCE);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(10);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
