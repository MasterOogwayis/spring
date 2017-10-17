package com.zsw.base.cache.interceptor;


import org.springframework.cache.interceptor.CacheOperation;

/**
 * @author ZhangShaowei on 2017/10/12 10:24
 */

public class CacheLockOperation extends CacheOperation {

    private final long timeOut;

    private final long expireTime;


    public CacheLockOperation(CacheLockOperation.Builder b) {
        super(b);
        this.timeOut = b.timeOut;
        this.expireTime = b.expireTime;
    }

    /**  */
    public long getTimeOut() {
        return timeOut;
    }

    /**  */
    public long getExpireTime() {
        return expireTime;
    }

    public static class Builder extends CacheOperation.Builder {

        private long timeOut;

        private long expireTime;

        /**  */
        public void setTimeOut(long timeOut) {
            this.timeOut = timeOut;
        }

        /**  */
        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }

        @Override
        protected StringBuilder getOperationDescription() {
            StringBuilder sb = super.getOperationDescription();
            sb.append(" | timeOut='");
            sb.append(this.timeOut);
            sb.append("'");
            sb.append(" | expireTime='");
            sb.append(this.expireTime);
            sb.append("'");
            return sb;
        }

        @Override
        public CacheLockOperation build() {
            return new CacheLockOperation(this);
        }
    }
}
