package com.zsw.metrics.mbean;

/**
 * @author ZhangShaowei on 2019/6/13 17:07
 **/
public class RemoteCallException extends RuntimeException {
    private static final long serialVersionUID = 7638086277338707302L;

    public RemoteCallException() {
    }

    public RemoteCallException(String message) {
        super(message);
    }

    public RemoteCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteCallException(Throwable cause) {
        super(cause);
    }

    public RemoteCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
