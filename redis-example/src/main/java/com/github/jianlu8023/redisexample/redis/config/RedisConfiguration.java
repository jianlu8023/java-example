package com.github.jianlu8023.redisexample.redis.config;

import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.lettuce.*;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfiguration {
    //    @Value("${spring.redis.host}")
    //    private String redisHost;
    //
    //    @Value("${spring.redis.port}")
    //    private int redisPort;

    //    @Bean
    //    public JedisPool jedisPool() {
    //        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    //        jedisPoolConfig.setMaxTotal(100);
    //        jedisPoolConfig.setMaxIdle(50);
    //        jedisPoolConfig.setMinIdle(10);
    //        jedisPoolConfig.setMaxWaitMillis(3000);
    //
    //        return new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
    //    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    //    @Bean
    //    public JedisConnectionFactory jedisConnectionFactory() {
    //        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    //        jedisConnectionFactory.setHostName("127.0.0.1");
    //        jedisConnectionFactory.setPort(6379);
    //        jedisConnectionFactory.setUsePool(true);
    //        return jedisConnectionFactory;
    //    }

    //    @Bean
    //    public LettuceConnectionFactory lettuceConnectionFactory() {
    //        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(redisHost, redisPort);
    //
    //        return new LettuceConnectionFactory(redisConfig);
    //    }
}
