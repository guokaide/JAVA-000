package io.kimmking.kmq.core;

public class KmqQueue {

    private KmqMessage[] kmqMessages;
    private int capacity;
    private int offset;

    public KmqQueue(int capacity) {
        this.capacity = capacity;
        this.kmqMessages = new KmqMessage[capacity];
        this.offset = -1;
    }

    public boolean offer(KmqMessage message) {
        if (offset == capacity) {
            return false;
        }
        offset++;
        kmqMessages[offset] = message;
        return true;
    }

    public KmqMessage poll(int offsetToBeConsumed, long timeoutMs) {
        long start = System.currentTimeMillis();
        while (offsetToBeConsumed > offset) {
            if (timeoutMs <= 0L || System.currentTimeMillis() - start > timeoutMs) {
                return null;
            }
        }
        return kmqMessages[offsetToBeConsumed];
    }

}
