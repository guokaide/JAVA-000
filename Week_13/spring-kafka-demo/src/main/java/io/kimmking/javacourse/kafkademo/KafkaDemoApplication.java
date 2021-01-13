package io.kimmking.javacourse.kafkademo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaDemoApplication implements CommandLineRunner {

    private final Producer producer;

    public KafkaDemoApplication(Producer producer) {
        this.producer = producer;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int i = 0;
        while (i++ < 100) {
            this.producer.sendMessage("test" + i);
        }
    }
}
