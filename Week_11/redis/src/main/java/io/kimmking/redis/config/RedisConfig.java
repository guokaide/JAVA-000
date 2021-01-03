package io.kimmking.redis.config;

import io.kimmking.redis.message.MessagePublisher;
import io.kimmking.redis.message.RedisMessagePublisher;
import io.kimmking.redis.message.RedisMessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return redisTemplate;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    @Bean
    MessagePublisher messagePublisher() {
        return new RedisMessagePublisher(redisTemplate(), topic());
    }

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListenerAdapter(), topic());
        return container;
    }

}
