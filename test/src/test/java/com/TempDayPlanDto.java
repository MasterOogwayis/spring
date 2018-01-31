package com;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ZhangShaowei on 2017/12/12 9:38
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TempDayPlanDto {

    /**
     *
     */
    private Integer dayOfMonth;

    /**
     * 任务总数
     */
    private Integer number;

    /**
     * 状态
     *
     * {@link PlanType.type}
     */
    private Integer type;


    /**  */
    public Integer getNumber() {
        return number;
    }

    /**  */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**  */
    public Integer getType() {
        return type;
    }

    /**  */
    public void setType(Integer type) {
        this.type = type;
    }

    /**  */
    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    /**  */
    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    /**
     * @param dayOfMonth
     * @param number
     * @param type
     */
    public TempDayPlanDto(Integer dayOfMonth, Integer number, Integer type) {
        this.dayOfMonth = dayOfMonth;
        this.number = number;
        this.type = type;
    }

    public TempDayPlanDto() {
        this.number = 0;
    }

    /**
     * @param number
     */
    public void addNumber(Integer number){
        this.number += number;
    }

    @Override
    public String toString() {
        return "TempDayPlanDto{" +
                "dayOfMonth=" + dayOfMonth +
                ", number=" + number +
                ", type=" + type +
                '}';
    }
}
