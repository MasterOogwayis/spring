package com.zsw.orm.client.exception;

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

    /**
     * @param type
     */
    public ApiException(BaseType type) {
        this.type = type;
    }

    /**
     * @param type
     * @param message
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

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ": {0x"
                + Integer.toHexString(this.type.getCode()) + ","
                + this.type.getMessage() + "}" + (null == this.getMessage() ? "" : this.getMessage());
    }

}
