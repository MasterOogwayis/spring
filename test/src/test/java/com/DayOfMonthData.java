package com;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 这是用车计划的数据结构
 *
 * @author ZhangShaowei on 2017/12/4 14:04
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DayOfMonthData {

    /**
     * vehicleId
     */
    private Long vehicleId;

    /**
     * 对应日期的：天
     */
    private Integer dayOfMonth;

    /**
     * 当天所有派工单
     */
    private Integer total;

    /**
     * 未完成状态派工单的数量
     */
    private Integer unfinished;

    /**
     * 待执行派工单数量
     */
    private Integer toBeExecuted;


    /**  */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**  */
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**  */
    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    /**  */
    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    /**  */
    public Integer getTotal() {
        return total;
    }

    /**  */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**  */
    public Integer getUnfinished() {
        return unfinished;
    }

    /**  */
    public void setUnfinished(Integer unfinished) {
        this.unfinished = unfinished;
    }

    /**  */
    public Integer getToBeExecuted() {
        return toBeExecuted;
    }

    /**  */
    public void setToBeExecuted(Integer toBeExecuted) {
        this.toBeExecuted = toBeExecuted;
    }

    /**
     * @param num
     */
    public void totalAdd(Integer num) {
        this.total += num;
    }

    /**
     * @param num
     */
    public void unfinishedAdd(Integer num) {
        this.unfinished += num;
    }

    /**
     * @param num
     */
    public void toBeExecutedAdd(Integer num) {
        this.toBeExecuted += num;
    }

}
