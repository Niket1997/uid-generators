package org.niket.snowflake;

import java.time.Instant;

public class Snowflake {
    private static final int BITS_IN_TIMESTAMP = 41;
    private static final int BITS_IN_NODE = 10;
    private static final int BITS_IN_SEQUENCE = 13;
    private static final int BITS_UNUSED = 1;
    private static final long maxSequenceId = (1L << BITS_IN_SEQUENCE) - 1;
    private static final long maxNodeId = (1L << BITS_IN_NODE) - 1;
    // Custom Epoch (January 1, 2023 Midnight UTC = 2023-01-01T00:00:00Z)
    private static final long DEFAULT_CUSTOM_EPOCH = 1672531200000L;

    private final long nodeId;
    private final long customEpoch;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    // create snowflake with given nodeId & custom epoch
    public Snowflake(long nodeId, long customEpoch) {
        if (nodeId < 0 || nodeId > maxNodeId) {
            throw new IllegalArgumentException(String.format("node ID must be in between %d & %d", 0L, maxNodeId));
        }

        this.nodeId = nodeId;
        this.customEpoch = customEpoch;
    }

    // create snowflake with given nodeId & default custom epoch
    public Snowflake(long nodeId) {
        this(nodeId, DEFAULT_CUSTOM_EPOCH);
    }

    public synchronized long getId() {
        long currentTimestamp = getCurrentTimestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid system clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequenceId;
            if (sequence == 0L) {
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            // reset sequence
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        return currentTimestamp << (BITS_IN_NODE + BITS_IN_SEQUENCE) |
                (nodeId << BITS_IN_SEQUENCE) |
                sequence;
    }

    private long getCurrentTimestamp() {
        return Instant.now().toEpochMilli() - customEpoch;
    }

    // block & wait till next millisecond
    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = getCurrentTimestamp();
        }
        return currentTimestamp;
    }

    public long[] parse(long id) {
        long maskNodeId = ((1L << BITS_IN_NODE) - 1) << BITS_IN_SEQUENCE;
        long maskSequence = (1L << BITS_IN_SEQUENCE) - 1;

        long timestamp = (id >> (BITS_IN_NODE + BITS_IN_SEQUENCE)) + customEpoch;
        long nodeId = (id & maskNodeId) >> BITS_IN_SEQUENCE;
        long sequenceId = id & maskSequence;

        return new long[]{timestamp, nodeId, sequenceId};
    }

    @Override
    public String toString() {
        return "Snowflake settings: [BITS_IN_EPOCH_TIMESTAMP: " + BITS_IN_TIMESTAMP + ", BITS_IN_NODE: " + BITS_IN_NODE +
                ", BITS_IN_SEQUENCE: " + BITS_IN_SEQUENCE + ", custom epoch: " + customEpoch + ", nodeId: " + nodeId;
    }
}
