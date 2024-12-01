package com.yoon.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class StockPersistenceAdapter {
    private final RedisTemplate<String, String> redisTemplate;

    private Long ALIVE_24H = 24 * 60 * 60L;

    public void store(String key, String value) {
        redisTemplate.opsForValue().set(key, value, ALIVE_24H, TimeUnit.SECONDS);
    }

    public String find(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}
