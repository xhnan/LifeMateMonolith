package com.xhn.base;

public class ShortSnowflakeIdGenerator {
    // 设置更接近当前时间的起始时间戳
    private final static long START_TIMESTAMP = 1719792000000L; // 2024-07-01

    // 减少时间戳位数，保留24位
    private final static long TIMESTAMP_BITS = 24L;
    private final static long MAX_DELTA_SECONDS = ~(-1L << TIMESTAMP_BITS); // 约194天

    // 机器标识保持3位
    private final static long MACHINE_ID_BITS = 3L;
    private final static long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);

    // 序列号使用5位
    private final static long SEQUENCE_BITS = 5L;
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    private final long machineId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public ShortSnowflakeIdGenerator(long machineId) {
        if (machineId > MAX_MACHINE_ID || machineId < 0) {
            throw new IllegalArgumentException("Machine ID out of range");
        }
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.");
        }

        // 转换为秒级时间戳差值，减少位数
        long deltaSeconds = (timestamp - START_TIMESTAMP) / 1000;

        if (deltaSeconds > MAX_DELTA_SECONDS) {
            throw new RuntimeException("Time bits exhausted, please adjust START_TIMESTAMP");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                while ((timestamp = System.currentTimeMillis()) <= lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        long id = (deltaSeconds << (MACHINE_ID_BITS + SEQUENCE_BITS))
                | (machineId << SEQUENCE_BITS)
                | sequence;

        return id;
    }
}