package com.zsw.service.timedtask.impl;

import com.zsw.base.service.impl.BaseServiceImpl;
import com.zsw.persistence.user.bean.TimedTask;
import com.zsw.persistence.user.repository.TimedTaskRepository;
import com.zsw.service.timedtask.TimedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * TimedTaskServiceImpl
 *
 * @author ZhangShaowei on 2018/4/19 14:17
 **/
@Service
public class TimedTaskServiceImpl extends BaseServiceImpl<TimedTask, Long> implements TimedTaskService {

    /**
     *
     */
    @Autowired
    private TimedTaskRepository timedTaskRepository;

    @Override
    public JpaRepository<TimedTask, Long> getRepository() {
        return this.timedTaskRepository;
    }
}
