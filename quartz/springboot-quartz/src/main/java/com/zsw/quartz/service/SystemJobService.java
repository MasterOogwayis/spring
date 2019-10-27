package com.zsw.quartz.service;

import com.zsw.quartz.persistence.entity.SystemJob;

import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator on 2019/10/27 16:58
 **/
public interface SystemJobService {
    /**
     * 获取任务数量
     *
     * @param
     * @return
     */
    public int getJobCount();

    /**
     * 查询定时任务列表
     *
     * @param map
     * @return
     */
    List<SystemJob> querySysJobList(HashMap<String, String> map);

    /**
     * 新增定时任务
     *
     * @param record
     * @return
     */
    int insertSelective(SystemJob record);

    /**
     * 删除定时任务
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据主键查询定时任务详情
     *
     * @param id
     * @return
     */
    SystemJob selectByPrimaryKey(Integer id);

    /**
     * 根据bean查询定时任务详情
     *
     * @param
     * @return
     */
    SystemJob selectByBean(SystemJob bean);

    /**
     * 更新定时任务详情
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(SystemJob bean);

}
