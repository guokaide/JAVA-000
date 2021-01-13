package io.kimmking.javacourse.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @KafkaListener(topics = "test32", groupId = "test")
    public void consume(String message) {
        log.info("Consume message => {}", message);
    }
}
