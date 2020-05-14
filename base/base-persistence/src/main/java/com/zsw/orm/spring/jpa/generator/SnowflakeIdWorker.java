package com.zsw.orm.spring.jpa.generator;

import lombok.extern.slf4j.Slf4j;

import java.time.Year;
import java.time.ZoneId;

/**
 * 优点：有序，分区实例不重复的 id 生成器
 * 缺点：如果1毫秒内生成数量超过 4096 个则会重复
 * <p>
 * id = 1 + 41 + 10 + 12 = 64位二进制 -> long
 * <p>
 * 1 bit：不用，为啥呢？因为二进制里第一个 bit 位如果是 1，那么都是负数，但是我们生成的 id 都是正数，所以第一个 bit 统一都是 0。
 * 41 bit：表示的是时间戳，单位是毫秒。41 bit 可以表示的数字多达 2^41 - 1，也就是可以标识 2^41 - 1 个毫秒值，换算成年就是表示69年的时间。
 * 10 bit：记录工作机器 id，代表的是这个服务最多可以部署在 2^10台机器上哪，也就是1024台机器。但是 10 bit 里 5 个 bit 代表机房 id，5 个 bit 代表机器 id。意思就是最多代表 2^5个机房（32个机房），每个机房里可以代表 2^5 个机器（32台机器）。
 * 12 bit：这个是用来记录同一个毫秒内产生的不同 id，12 bit 可以代表的最大正整数是 2^12 - 1 = 4096，也就是说可以用这个 12 bit 代表的数字来区分同一个毫秒内的 4096 个不同的 id。
 *
 * @author ZhangShaowei on 2020/4/1 10:16
 */
@Slf4j
public class SnowflakeIdWorker implements IdWorker {


    /**
     * 设定一个初始时间，不要改变它  2010-01-01 00:00:00
     */
    private static final long TWEPOCH = Year.of(2010).atMonth(1).atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

    private static final long WORKER_ID_BITS = 5L;
    private static final long DATA_CENTER_ID_BITS = 5L;

    /**
     * 这个是二进制运算，就是 5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
     */
    private long maxWorkerId = ~(-1L << WORKER_ID_BITS);

    /**
     * 这个是一个意思，就是 5 bit最多只能有31个数字，机房id最多只能是32以内
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    private long lastTimestamp = -1L;

    /**
     * 0 < workerId < 32
     */
    private long workerId;

    /**
     * 0 < datacenterId < 32
     */
    private long datacenterId;

    private long sequence;

    public SnowflakeIdWorker(long workerId, long dataCenterId, long sequence) {
        this.workerId = workerId;
        this.datacenterId = dataCenterId;
        this.sequence = sequence;
    }

    /**
     *
     */
    public long getDatacenterIdBits() {
        return DATA_CENTER_ID_BITS;
    }

    @Override
    public synchronized Long nextId() {
        long timestamp = this.timeGen();
        if (timestamp < this.lastTimestamp) {
            log.warn("clock is moving backwards.  Rejecting requests until {}.", lastTimestamp);
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (timestamp == this.lastTimestamp) {
            // 一个毫秒内最多只能有 4096 个数字
            // 无论传多少进来 位运算始终让他保持在 4096 以下
            // 4096 & 4097 -> 0 1111 1111 1111 & 1 0000 0000 0001 = 1
            this.sequence = (sequence + 1) & SEQUENCE_MASK;
        } else {
            this.sequence = 0;
        }

        // 最近一次生成 id 的时间戳
        this.lastTimestamp = timestamp;


        // 这儿就是将时间戳左移，放到 41 bit那儿；
        // 将机房 id 左移放到 5 bit 那儿；
        // 将机器 id 左移放到 5 bit 那儿；将序号放最后 12 bit；
        // 最后拼接起来成一个 64 bit的二进制数字，转换成 10 进制就是个 long 型
        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATA_CENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }


    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }


}
