package com;

import java.util.Objects;

/**
 * 派工单状态参数
 *
 * @author ZhangShaowei on 2017/12/12 14:10
 */
public enum DispatchOrderStatus {

    /**
     * 待执行
     */
    NEW(0, "待执行"),

    /**
     * 执行中
     */
    EXECUTING(1, "执行中"),
    /**
     *
     */
    UNFINISHED(5, "未完成"),
    /**
     *
     */
    DELETE(6, "已删除"),
    /**
     *
     */
    CANCEL(7, "已取消"),
    /**
     *
     */
    CHANGED(8, "已改派"),
    /**
     *
     */
    COMPLETED(9, "已完成"),


    /**
     * 计划可能会用到
     */
    OTHER(99, "未开始");

    /**
     * 状态值
     */
    private int value;


    /**
     * 字段说明
     */
    private String desc;

    /**
     * @param value 状态值
     * @param desc   字段说明
     */
    DispatchOrderStatus(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**  */
    public int getValue() {
        return value;
    }

    /**  */
    public String getDesc() {
        return desc;
    }

    /**
     * @param code status
     * @return
     */
    public static DispatchOrderStatus getOrderStatus(final int code) {
        for (DispatchOrderStatus value : DispatchOrderStatus.values()) {
            if (Objects.equals(value.getValue(), code)) {
                return value;
            }
        }
        return OTHER;
    }


}
