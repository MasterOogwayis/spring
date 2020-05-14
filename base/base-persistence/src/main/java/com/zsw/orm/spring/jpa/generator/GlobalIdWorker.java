package com.zsw.orm.spring.jpa.generator;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 可独立运行的全局ID生成器，保持趋势递增，线程安全，尾数0至9随机分布。
 * 41位(自动扩展位数)毫秒+48Mac地址+13位累加数(每毫秒之后保留后5位)。
 * 理想情况平均每秒可生成8190968个，实测生成百万个用时不到2秒(视配置而定)。
 * 使用32位10进制可用到3300年后，使用21位36进制可用到8500年后，可扩展长度。
 *
 * @author Ewing
 */
public class GlobalIdWorker implements IdWorker {
    // Mac地址掩码（48个1）
    private final static long macAddressMask = ~(-1L << 48L);
    // Mac标志位 保证长度一定是48+1位 再用substring去掉标志位
    private final static long macAddressFlag = 1L << 48L;

    // 序号掩码（13个1）也是最大值
    private final static long sequenceMask = ~(-1L << 13L);
    // 序号标志位 保证长度一定是13+1位 再用substring去掉标志位
    private final static long sequenceFlag = 1L << 13L;

    private static long lastTimestamp = System.currentTimeMillis();

    private static long sequence = 0L;

    private static String macAddressBit;

    // 尾数离散度 可以分散ID的尾数分布
    private final static long discreteRate = 1L << 5L;

    // String类型的ID小于该值填充0 保证长度为21位
    private final static BigInteger fillFlag = new BigInteger("100000000000000000000", 36);

    /**
     * 初始化worker
     */
    static {
        try {
            long macAddress = macAddressLong();
            macAddressBit = Long.toBinaryString((macAddress & macAddressMask) | macAddressFlag).substring(1);
        } catch (IOException e) {
            throw new RuntimeException("Init mac address fail.", e);
        }
    }

    /**
     * 获取机器的Mac地址（48位）
     */
    private static long macAddressLong() throws IOException {
        byte[] macs = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
        int shift = 0;
        long macLong = 0;
        for (int i = 0; i < macs.length; i++) {
            macLong = (macLong << shift) | (macs[i] & 0xFF);
            shift += 8;
        }
        return macLong;
    }

    /**
     * 生成唯一ID
     */
    public static synchronized BigInteger nextBigInteger() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            tilNextMillis(lastTimestamp);
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，计数器增加，满了则为0
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一毫秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = sequence & discreteRate;
        }

        lastTimestamp = timestamp;

        // ID偏移组合生成最终的ID，并返回ID
        String idBit = Long.toBinaryString(timestamp) + macAddressBit +
                Long.toBinaryString(sequence | sequenceFlag).substring(1);

        return new BigInteger(idBit, 2);
    }

    /**
     * 循环到下一毫秒
     */
    private static long tilNextMillis(final long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前时间
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取36进制21位长度的String类型的ID
     */
    public static String nextString() {
        BigInteger integer = nextBigInteger();
        return integer.compareTo(fillFlag) < 0 ? "0" + integer.toString(36) : integer.toString(36);
    }

    @Override
    public Long nextId() {
        return 0L;
    }

    public static void main(String[] args) {
        System.out.println(nextBigInteger());
        System.err.println(nextString());
    }

}