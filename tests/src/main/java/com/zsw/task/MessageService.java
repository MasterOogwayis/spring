package com.zsw.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2021/3/2 17:08
 */
@Slf4j
@Service
public class MessageService {

    @Lazy
    @Autowired
    private TaskService taskService;


    @Async
    public void runTask() {
        log.info("runTask {}", this);
    }

    public void run() {
        this.taskService.run();
    }

}
