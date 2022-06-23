//package com.zsw.redis;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.health.NamedContributors;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author ZhangShaowei on 2021/10/15 15:57
// */
//@Slf4j
//@RequestMapping("redis")
//@RestController
//public class RedisController implements InitializingBean {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Autowired
//    private List<NamedContributors<?>> namedContributors;
//
//    @Autowired
//    private ThreadPoolTaskExecutor executor;
//
//    private SmsQueue queue;
//
//
//    @GetMapping("push")
//    public Object push(@RequestParam("value") String value) {
//        this.queue.offer(value);
//        return "success";
//    }
//
//    @GetMapping("set")
//    public Object set(@RequestParam("key") String key, @RequestParam("value") String value) {
//        this.redisTemplate.opsForValue().set(key, value);
//        return "success";
//    }
//
//    @GetMapping("get")
//    public Object get(@RequestParam("key") String key) {
//        return this.redisTemplate.opsForValue().get(key);
//    }
//
//    @GetMapping("test")
//    public Object test() {
//        this.namedContributors.stream().flatMap(NamedContributors::stream).forEach(c -> {
//            Object contributor = c.getContributor();
//            log.info("name = {}, {}", c.getName(), contributor);
//        });
//        return "123";
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        this.queue = new SmsQueue("queue:sms", this.redisTemplate);
//
//        this.executor.execute(() -> {
//            while (!Thread.currentThread().isInterrupted()) {
//                try {
//                    Object poll = this.queue.poll(5, TimeUnit.SECONDS);
//                    if (Objects.isNull(poll)) {
//                        log.info("无消息 ...");
//                    } else {
//                        log.info("收到消息:{}", poll);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    log.error("拉取消息失败: {}", e.getMessage(), e);
//                }
//            }
//            log.info("结束任务 ...");
//        });
//    }
//}
