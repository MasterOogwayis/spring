package com.zsw.persistence.timedtask.bean;

import com.zsw.orm.bean.commons.BaseBean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TimedTask
 *
 * @author ZhangShaowei on 2018/4/19 14:10
 **/
@Getter
@Setter
@Entity
@Table(name = "TIMED_TASK")
public class TimedTask extends BaseBean {

    public static final Integer STATUS_ENABLED = 1;
    public static final Integer STATUS_DISABLED = 0;

    private static final long serialVersionUID = -8927964341445352874L;

    /**
     *
     */
    @Column(name = "JOB_NAME", nullable = false)
    private String jobName;

    /**
     *
     */
    @Column(name = "CRON", nullable = false)
    private String cron;

    /**
     * 状态 0 停用  1启用
     */
    @Column(name = "STATUS", nullable = false)
    private Integer status;

    /**
     *
     */
    @Column(name = "MARK", nullable = false)
    private String mark;

    /**
     *
     */
    @Column(name = "JOB_DESC")
    private String jobDesc;


}
