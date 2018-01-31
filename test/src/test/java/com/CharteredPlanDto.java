package com;

/**
 * @author ZhangShaowei on 2017/12/12 15:40
 */
public class CharteredPlanDto extends BaseDto {
    private static final long serialVersionUID = -4124398016091050463L;

    /**
     * 派工单 id
     */
    private Long id;

    /**
     * 派工单状态
     */
    private Integer status;

    /**
     * 包车线路名称
     */
    private String lineName;

    /**
     * 驾驶员
     */
    private String driver;

    /**
     * 客户
     */
    private String contact;

    /**  */
    public Long getId() {
        return id;
    }

    /**  */
    public void setId(Long id) {
        this.id = id;
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
    public String getLineName() {
        return lineName;
    }

    /**  */
    public void setLineName(String lineName) {
        this.lineName = lineName;
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
    public String getContact() {
        return contact;
    }

    /**  */
    public void setContact(String contact) {
        this.contact = contact;
    }
}
