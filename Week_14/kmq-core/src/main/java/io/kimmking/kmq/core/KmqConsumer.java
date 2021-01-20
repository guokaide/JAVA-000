package io.kimmking.kmq.core;

public class KmqConsumer<T> {

    private final KmqBroker broker;

    private Kmq kmq;

    private String consumerName;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public KmqMessage<T> poll(long timeout) {
        return kmq.poll(consumerName, timeout);
    }

}
