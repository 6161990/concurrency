package com.yoon.facade;

import com.yoon.aop.RedLock;
import com.yoon.domain.Item;
import com.yoon.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {

    private final RedissonClient redissonClient;
    private final ItemService itemService;

    @Transactional
    public void decrease(Long id, Long quantity) {
        RLock lock = redissonClient.getLock(id.toString());
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.tryLock(5000, 3000, TimeUnit.MILLISECONDS);
            if (lockAcquired) {
                itemService.decrease(id, quantity);

                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            } else {
                throw new RuntimeException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void decreaseAop(Long id, Long quantity) {
        itemService.decreaseAop(id, quantity);
    }

}

