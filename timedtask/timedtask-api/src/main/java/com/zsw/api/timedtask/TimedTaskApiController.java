package com.zsw.api.timedtask;

import com.zsw.base.api.commons.BaseApiController;
import com.zsw.service.timedtask.TimedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TimedTaskApiController
 *
 * @author ZhangShaowei on 2018/4/19 14:21
 **/
@RestController
@RequestMapping("user")
public class TimedTaskApiController extends BaseApiController {

    /**
     *
     */
    @Autowired
    private TimedTaskService timedTaskService;


}
