package com.lazarev.rediscachejedis.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.*;

@Configuration
public class RedisConfig {
    private static final CacheKeyPrefix CACHE_KEY_PREFIX = cacheName -> cacheName + ":";

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(RedisProperties properties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());

        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisCacheConfiguration defaultCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .computePrefixWith(CACHE_KEY_PREFIX)
                .disableCachingNullValues()
                .serializeValuesWith(fromSerializer(
                        new GenericJackson2JsonRedisSerializer())
                );
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer cacheConfigurationCustomizer(
                                              RedisCacheConfiguration defaultCacheConfig) {
        return (builder) -> builder
                .withCacheConfiguration("employees",
                        defaultCacheConfig.entryTtl(Duration.ofMinutes(10)))
                .withCacheConfiguration("employee-infos",
                        defaultCacheConfig.entryTtl(Duration.ofMinutes(5)));
    }
}
