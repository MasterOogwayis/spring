package com.zsw.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2021/3/2 17:09
 */
@Service
public class TaskService {

    @Lazy
    @Autowired
    private MessageService messageService;

    @Async
    public void run() {
        this.messageService.runTask();
    }

}
