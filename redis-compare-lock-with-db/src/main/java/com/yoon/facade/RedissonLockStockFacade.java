package com.yoon.facade;

import com.yoon.domain.Item;
import com.yoon.repository.ItemRepository;
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
    private final ItemRepository itemRepository;

    @Transactional
    public void decrease(Long id, Long quantity) {
        RLock lock = redissonClient.getLock(id.toString());
        try {
            if (lock.tryLock(5000,3000, TimeUnit.MILLISECONDS)) {
                Item item = itemRepository.findById(id).orElseThrow();
                item.decrease(quantity);
                itemRepository.save(item);
            } else {
                throw new RuntimeException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
