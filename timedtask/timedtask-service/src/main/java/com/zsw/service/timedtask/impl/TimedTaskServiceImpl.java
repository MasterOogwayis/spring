package com.zsw.service.timedtask.impl;

import com.zsw.orm.service.impl.BaseDataServiceImpl;
import com.zsw.persistence.timedtask.bean.TimedTask;
import com.zsw.persistence.timedtask.repository.TimedTaskRepository;
import com.zsw.service.timedtask.TimedTaskService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TimedTaskServiceImpl
 *
 * @author ZhangShaowei on 2018/4/19 14:17
 **/
@Getter
@Service
public class TimedTaskServiceImpl extends BaseDataServiceImpl<TimedTask, Long> implements TimedTaskService {

    /**
     *
     */
    @Autowired
    private TimedTaskRepository repository;
}
