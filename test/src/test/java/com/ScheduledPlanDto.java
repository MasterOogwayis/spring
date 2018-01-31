package com;

/**
 * @author ZhangShaowei on 2017/12/12 14:57
 */

public class ScheduledPlanDto extends BaseDto {

    private static final long serialVersionUID = -8223889974591881777L;
    /**
     * 派工单 id
     */
    private Long id;

    /**
     * 计划 id
     */
    private Long planId;

    /**
     * 线路名称
     */
    private String linePlanName;

    /**
     * 主驾
     */
    private String driver;

    /**
     * 班次
     */
    private String timeScope;

    /**
     * 班次类型 定班车 0 / 滚班车 1
     */
    private Integer type;

    /**
     * 运行类型：0：去程，1：返程
     */
    private Integer runType;

    /**
     * 调度来源 0 后台 ， 1 驾驶端app
     */
    private Integer dispatchSource;

    /**
     * 99 是未开始的计划
     */
    private Integer status;


    /**  */
    public Long getId() {
        return id;
    }

    /**  */
    public void setId(Long id) {
        this.id = id;
    }

    /**  */
    public Long getPlanId() {
        return planId;
    }

    /**  */
    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    /**  */
    public String getLinePlanName() {
        return linePlanName;
    }

    /**  */
    public void setLinePlanName(String linePlanName) {
        this.linePlanName = linePlanName;
    }

    /**  */
    public String getDriver() {
        return driver;
    }

    /**  */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**  */
    public String getTimeScope() {
        return timeScope;
    }

    /**  */
    public void setTimeScope(String timeScope) {
        this.timeScope = timeScope;
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
    public Integer getStatus() {
        return status;
    }

    /**  */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**  */
    public Integer getRunType() {
        return runType;
    }

    /**  */
    public void setRunType(Integer runType) {
        this.runType = runType;
    }

    /**  */
    public Integer getDispatchSource() {
        return dispatchSource;
    }

    /**  */
    public void setDispatchSource(Integer dispatchSource) {
        this.dispatchSource = dispatchSource;
    }
}
