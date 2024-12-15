package com.lazarev.redismessagequeue.config;

import com.lazarev.redismessagequeue.annotation.RedisListener;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class RedisConfig implements EmbeddedValueResolverAware {
    private static final Pattern ENV_PATTERN = Pattern.compile("^\\$\\{.*}$");

    private StringValueResolver resolver;

    private final List<MessageListener> messageListeners;


    @Bean
    JedisConnectionFactory redisConnectionFactory(RedisProperties properties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());

        return new JedisConnectionFactory(config);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        messageListeners.forEach(listener -> {
            String channel = extractRedisListenerChannel(listener);
            container.addMessageListener(
                    new MessageListenerAdapter(listener), new ChannelTopic(channel)
            );
        });

        return container;
    }

    @Override
    public void setEmbeddedValueResolver(@NonNull StringValueResolver resolver) {
        this.resolver = resolver;
    }

    private String extractRedisListenerChannel(MessageListener listener){
        RedisListener listenerAnnotation = listener.getClass().getAnnotation(RedisListener.class);
        if(listenerAnnotation == null){
            throw new IllegalStateException("RedisMessageListener should be annotated with @RedisListener");
        }

        String channel = listenerAnnotation.value();
        if(ENV_PATTERN.matcher(channel).matches()){
            channel = resolver.resolveStringValue(channel);
        }

        return channel;
    }
}
