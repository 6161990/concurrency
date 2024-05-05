package com.yoon.facade;

import com.yoon.service.StockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockStockFacade {

    private final RedissonClient redissonClient;
    private final StockService stockService;

    public RedissonLockStockFacade(RedissonClient redissonClient, StockService stockService) {
        this.redissonClient = redissonClient;
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity){
        RLock lock = redissonClient.getLock(id.toString());

        try {
            // 몇 초 동안 lock 획득을 시도하고 그것을 몇초 동안 점유할 것인지
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if(!available){
                System.out.println("lock 획득 실패");
                return;
            }

            stockService.decrease(id, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
