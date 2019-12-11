package com.laomukuq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig  {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){

        RedisTemplate<String,Object> template = new RedisTemplate<String, Object>();

        template.setConnectionFactory(factory);

        template.setKeySerializer(new StringRedisSerializer());
        // 实例化字符串
        // template.setValueSerializer(new StringRedisSerializer());
        // 实例化对象
        // template.setValueSerializer(new JdkSerializationRedisSerializer());

        return template;
    }
}