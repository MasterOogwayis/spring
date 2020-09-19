package com.zsw.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * FIXME 不使用 16 进制
 * 描述：基本的异常类型
 * 异常都会跳转到对应错误页面。如果是ajax请求由js脚本解释错误页面统一处理。
 *
 * @author ZhangShaowei
 * @version 2.0.0
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
    SYSTEM_EXCEPTION(-1, "系统异常");

    private final Integer code;

    private final String message;


    /**
     * @return String
     */
    @Override
    public String toString() {
        return "{" + this.code + "," + this.message + "}";
    }
}
