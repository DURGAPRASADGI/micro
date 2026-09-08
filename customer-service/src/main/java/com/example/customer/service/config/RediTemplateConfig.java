package com.example.customer.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class RediTemplateConfig {
	

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(connectionFactory);
	    // Pass objectMapper directly to the constructor
	    GenericJacksonJsonRedisSerializer jsonSerializer = new GenericJacksonJsonRedisSerializer(objectMapper);
	    template.setKeySerializer(RedisSerializer.string());
	    template.setHashKeySerializer(RedisSerializer.string());
	    template.setValueSerializer(jsonSerializer);
	    template.setHashValueSerializer(jsonSerializer);
	    template.afterPropertiesSet();
	    return template;
	}

}
