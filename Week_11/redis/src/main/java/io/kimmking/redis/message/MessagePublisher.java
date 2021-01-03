package io.kimmking.redis.message;

public interface MessagePublisher {

    void publish(final String message);

}
