package com.zsw.exception;

import java.util.Objects;
import java.util.Optional;

/**
 * @author ZhangShaowei on 2017/6/9 15:33
 */

public class ApiException extends RuntimeException {


    private static final long serialVersionUID = 5185227558479370182L;
    /**
     *
     */
    private BaseType type;

    public ApiException(String message) {
        super(message);
        this.type = BaseExceptionType.FAILED;
    }

    public ApiException(String message, Throwable throwable) {
        super(message, throwable);
        this.type = BaseExceptionType.FAILED;
    }

    /**
     * @param type
     */
    public ApiException(BaseType type) {
        this.type = type;
    }

    /**
     * @param type
     * @param message 顶级错误消息
     */
    public ApiException(BaseType type, String message) {
        super(message);
        this.type = type;
    }

    /**
     * @param type
     * @param cause
     */
    public ApiException(BaseType type, Throwable cause) {
        super(cause);
        this.type = type;
    }

    /**
     * @param type
     * @param message
     * @param cause
     */
    public ApiException(BaseType type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    /**
     * @return
     */
    public BaseType getType() {
        return this.type;
    }

    /**
     * @param type
     */
    protected void setType(BaseType type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(this.type)
                // 忽略 FAIL 异常，提示信息无意义
                .filter(baseType -> !Objects.equals(baseType, BaseExceptionType.FAILED))
                .map(BaseType::getMessage)
                .map(this::concat)
                .orElseGet(ApiException.super::getMessage);
    }


    /**
     * 拼接错误消息
     *
     * @param message
     * @return
     */
    private String concat(String message) {
        return Optional.ofNullable(super.getMessage())
                .map(msg -> msg + "：" + message)
                .orElse(message);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.type.getCode() + "," + this.getMessage();
    }

}
