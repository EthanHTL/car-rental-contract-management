package com.example.carrentalcontract.common;

import org.springframework.stereotype.Component;

/**
 * @Author huangtianliang
 * @Description //TODO 雪花算法
 * @Date
 * @Version 1.0
 **/
public class ObjectIdGenerator {
    private final long twepoch = 1420041600000L;
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long maxWorkerId = 31L;
    private final long maxDatacenterId = 31L;
    private final long sequenceBits = 12L;
    private final long workerIdShift = 12L;
    private final long datacenterIdShift = 17L;
    private final long timestampLeftShift = 22L;
    private final long sequenceMask = 4095L;
    private long workerId;
    private long dataCenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public ObjectIdGenerator(long workerId, long dataCenterId) {
        this.setWorkerId(workerId);
        this.setDataCenterId(dataCenterId);
    }

    public synchronized long newId() {
        long timestamp = this.timeGen();
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        } else {
            if (this.lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & 4095L;
                if (this.sequence == 0L) {
                    timestamp = this.tilNextMillis(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            long result = timestamp - 1420041600000L << 22 | this.dataCenterId << 17 | this.workerId << 12 | this.sequence;
            return result;
        }
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public void setDataCenterId(long dataCenterId) {
        if (dataCenterId <= 31L && dataCenterId >= 0L) {
            this.dataCenterId = dataCenterId;
        } else {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
        }
    }

    public void setWorkerId(long workerId) {
        if (workerId <= 31L && workerId >= 0L) {
            this.workerId = workerId;
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
        }
    }
}
