package com.zsw.orm.client.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhangShaowei on 2017/6/9 15:39
 */
@Getter(onMethod_ = @Override)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BaseExceptionType implements BaseType {

    /**
     * 成功
     */
    SUCCESS(0, "success"),
    FAILED(-1, "failed"),
    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION(0x99999, "系统异常");

    /**
     *
     */
    private Integer code;

    /**
     *
     */
    private String message;


    @Override
    public String toString() {
        return "{0x" + Integer.toHexString(this.getCode()) + "," + this.getMessage() + "}";
    }
}
