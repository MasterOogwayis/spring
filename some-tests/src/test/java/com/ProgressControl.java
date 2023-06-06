package com;

/**
 * @author zhangshaowei on 2023/6/6 14:27
 */
public class ProgressControl {

    private final long total;

    private final long startTime;

    private long processed;

    public ProgressControl(long total) {
        this.total = total;
        this.startTime = System.currentTimeMillis();
        this.processed = 0;
    }

    public void update(long processed) {
        this.processed = processed;
    }

    public long getRemainingTime() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        double progress = (double) processed / total;
        return (long) (elapsedTime / progress - elapsedTime);
    }

}
