package io.kimmking.javacourse.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Producer {

    private static final String TOPIC = "test32";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
      log.info("produce message => {}", message);
      this.kafkaTemplate.send(TOPIC, message);
    }
}
