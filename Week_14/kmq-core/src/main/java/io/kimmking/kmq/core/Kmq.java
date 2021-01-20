package io.kimmking.kmq.core;

import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Topic / Queue
public final class Kmq {

    private String topic;
    private int capacity;
    private KmqQueue queue;
    private Map<String, Integer> consumedOffsetMap = new ConcurrentHashMap<>(64);

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new KmqQueue(capacity);
    }

    public boolean send(KmqMessage message) {
        return queue.offer(message);
    }

    @SneakyThrows
    public KmqMessage poll(String consumerName, long timeout) {
        Integer consumedOffset = consumedOffsetMap.get(consumerName);
        int offsetToBeConsumed = consumedOffset == null ? 0 : consumedOffset + 1;
        KmqMessage message = queue.poll(offsetToBeConsumed, timeout);
        if (message == null) {
            return null;
        }
        consumedOffsetMap.put(consumerName, offsetToBeConsumed);
        return message;
    }

}
