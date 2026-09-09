package com.example.customer.service.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RediCachConfig {
	
	
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
	    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
	            .entryTtl(Duration.ofMinutes
	            		(5))
	            .disableCachingNullValues()
	            .serializeKeysWith(
	                    RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string())
	            )
	            .serializeValuesWith(
	                    RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json())
	            );

	    return RedisCacheManager.builder(connectionFactory)
	            .cacheDefaults(config)
	            .build();
	}

}
