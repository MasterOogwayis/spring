package com.zsw.quartz.exception;

/**
 * 自定义异常
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -6385585424246977031L;

    public BizException(String msg) {
        super(msg);
    }
}
