package com;

/**
 * @author ZhangShaowei on 2017/12/12 15:56
 */

public class RegularPlanDto extends BaseDto {
    private static final long serialVersionUID = 3959483660153828098L;

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Long planId;

    /**
     *
     */
    private String lineName;

    /**
     *
     */
    private String contact;

    /**
     *
     */
    private String driver;

    /**
     * 班次类型 定班车 0 / 滚班车 1
     */
    private Integer type;

    /**
     * 运行类型：0：去程，1：返程
     */
    private Integer runType;

    /**
     * 班次
     */
    private String timeScope;

    /**
     *
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
    public String getLineName() {
        return lineName;
    }

    /**  */
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    /**  */
    public String getContact() {
        return contact;
    }

    /**  */
    public void setContact(String contact) {
        this.contact = contact;
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
    public Integer getStatus() {
        return status;
    }

    /**  */
    public void setStatus(Integer status) {
        this.status = status;
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
    public Integer getRunType() {
        return runType;
    }

    /**  */
    public void setRunType(Integer runType) {
        this.runType = runType;
    }

    /**  */
    public String getTimeScope() {
        return timeScope;
    }

    /**  */
    public void setTimeScope(String timeScope) {
        this.timeScope = timeScope;
    }
}
