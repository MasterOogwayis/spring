package com;

/**
 * @author ZhangShaowei on 2017/12/12 16:37
 */

public class OtherPlanDto extends BaseDto {
    private static final long serialVersionUID = 7310014264211204399L;

    private String driver;

    private String type;

    private String reason;

    private String status;

    /**  */
    public String getDriver() {
        return driver;
    }

    /**  */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**  */
    public String getType() {
        return type;
    }

    /**  */
    public void setType(String type) {
        this.type = type;
    }

    /**  */
    public String getReason() {
        return reason;
    }

    /**  */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**  */
    public String getStatus() {
        return status;
    }

    /**  */
    public void setStatus(String status) {
        this.status = status;
    }
}
