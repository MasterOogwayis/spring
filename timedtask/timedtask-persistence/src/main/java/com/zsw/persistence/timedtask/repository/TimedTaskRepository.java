package com.zsw.persistence.timedtask.repository;

import com.zsw.persistence.timedtask.bean.TimedTask;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TimedTask Repository
 *
 * @author ZhangShaowei on 2018/4/19 14:13
 **/
@Repository
@Qualifier("timedTaskRepository")
public interface TimedTaskRepository extends JpaRepository<TimedTask, Long> {
}
