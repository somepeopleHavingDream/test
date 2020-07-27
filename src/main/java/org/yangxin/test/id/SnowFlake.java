package org.yangxin.test.id;

/**
 * 雪花算法，是Twitter开源的分布式id生成算法。
 * 其核心思想是：使用一个64bit的long型的数字作为全局唯一id。
 * 这64个bit中，其中1个bit是不用的，然后用其中41bit作为毫秒数，用10bit作为工作机器id，12bit作为序列号
 *
 * SnowFlake算法的优点：
 *  1. 高性能高可用：生成时不依赖于数据库，完全在内存中生成
 *  2. 容量大：每秒中能生成数百万的自增ID
 *  3. ID自增：存入数据库中，索引效率高
 *
 * SnowFlake算法的缺点：
 *  依赖与系统时间的一致性，如果系统时间被回调，或者改变，可能会造成id冲突或者重复
 */
@SuppressWarnings("unused")
public class SnowFlake {

    /**
     * 因为二进制里第一个bit位如果是1，那么都是负数，但是我们生成的id都是正数，所以第一个bit统一都是0
     * 机器ID 2进制5位 32位减掉1位 31个
     */
    private final long workerId;

    /**
     * 机房ID 2进制5位 32位减掉1位 31个
     */
    private final long dataCenterId;

    /**
     * 代表一毫秒内生成的多个id的最新序号 12位 4096-1=4095个
     */
    private long sequence;

    /**
     * 5位的机器id
     */
    private final long workerIdBits = 5L;

    /**
     * 5位的机房id
     */
    private final long dataCenterIdBits = 5L;

    /**
     * 记录产生时间毫秒数，判断是否是同一毫秒
     */
    private long lastTimestamp = -1L;

    public long getWorkerId() {
        return workerId;
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public SnowFlake(long workerId, long dataCenterId, long sequence) {
        // 检查机房id和机器id是否超过31，不能小于0
        /*
         * 这个是二进制运算，就是5bit最多只能有31个数字，也就是说机器id最多只能是32以内
         */
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }

        /*
         * 这个是一个意思，就是5bit最多只能有31个数字，机房id最多只能是32以内
         */
        long maxDataCenterId = ~(-1L << dataCenterIdBits);
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0",
                    maxDataCenterId));
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
    }

    /**
     * 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
     */
    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把sequence须后给递增1，最多就是4096
        /*
         * 每毫秒内产生的id树 2的12次方
         */
        long sequenceBits = 12L;
        if (lastTimestamp == timestamp) {
            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            // 这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过4096这个范围
            long sequenceMask = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;

            if (sequence == 0) {
                timestamp = untilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;

        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41bit那儿；将机房id左移放到5bit那儿；将机器id左移放到5bit那儿；将序号放最后12bit
        // 最后拼接起来成一个64bit的二进制数字，转换成10进制就是个long型
        /*
         * 设置一个时间初始值，2^41-1，差不多可以用69年
         */
        long twEpoch = 1585644268888L;
        long dataCenterIdShift = sequenceBits + workerIdBits;
        long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
        return ((timestamp - twEpoch) << timestampLeftShift
            | (dataCenterId << dataCenterIdShift)
            | (workerId << sequenceBits)
            | sequence);
    }

    /**
     * 当某一毫秒产生的id数，超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
     */
    private long untilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前时间戳
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }
}
