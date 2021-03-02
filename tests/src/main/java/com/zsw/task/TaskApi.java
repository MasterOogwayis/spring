package com.zsw.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/3/2 17:10
 */
@RequestMapping("task")
@RestController
public class TaskApi {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String run() {
        this.taskService.run();
        return "success";
    }

}
