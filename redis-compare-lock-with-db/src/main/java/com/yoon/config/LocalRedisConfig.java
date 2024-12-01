package com.yoon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Profile("local") // profile이 local일 때만 활성화
@Configuration
public class LocalRedisConfig {

    @Value("${spring.data.redis.port:6379}") // Spring Boot의 Redis 포트 설정
    private Integer redisPort;

    private RedisServer redisServer;

    @PostConstruct
    private void startRedis() {
        try {
            redisServer = new RedisServer(redisPort); // redisPort 주입 후 초기화
            redisServer.start();
            System.out.println("Embedded Redis Server started on port: " + redisPort);
        } catch (Exception exception) {
            System.err.println("Failed to start Embedded Redis Server: " + exception.getMessage());
        }
    }

    @PreDestroy
    private void stopRedis() {
        if (redisServer != null && redisServer.isActive()) {
            redisServer.stop();
            System.out.println("Embedded Redis Server stopped.");
        }
    }
}
