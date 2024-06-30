package com.yoon.facade;

import com.yoon.domain.Item;
import com.yoon.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {

    private final RedissonClient redissonClient;
    private final ItemRepository itemRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void decrease(Long id, Long quantity) {
        RLock lock = redissonClient.getLock(id.toString());
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.tryLock(5000, 3000, TimeUnit.MILLISECONDS);
            if (lockAcquired) {
                Item item = itemRepository.findById(id).orElseThrow();
                item.decrease(quantity);
                itemRepository.save(item);

                applicationEventPublisher.publishEvent(lock);
            } else {
                throw new RuntimeException("Failed to acquire lock");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void releaseLock(RLock lock) {
        if (lock.isLocked() && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

}

