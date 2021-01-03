package io.kimmking.redis.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {

    public static List<String> messages = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        messages.add(message.toString());
        log.info("received <" + new String(message.getBody()) + ">");
        // 消息处理，例如订单异步处理
        log.info("process " + new String(message.getBody()));
    }
}
