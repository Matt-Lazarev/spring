package com.lazarev.rediscachejedis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisConfig {

    @Bean
    public Jedis jedis(RedisProperties redisProperties){
        HostAndPort hostAndPort = new HostAndPort(
                redisProperties.getHost(),
                redisProperties.getPort()
        );
        return new Jedis(hostAndPort);
    }
}
