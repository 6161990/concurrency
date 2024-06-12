package com.yoon.facade;

import com.yoon.domain.Item;
import com.yoon.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

@Service
@RequiredArgsConstructor
public class LockService {

    private final ItemRepository itemRepository;
    private final EntityManager entityManager;

    @Transactional
    public void decrease(Long id, Long quantity) {
        itemRepository.findById(id).map(value -> {
            value.decrease(quantity);
            return value;
        }).ifPresent(itemRepository::save);
    }

    @Transactional
    public void decrease2(Long id, Long quantity) {
        Item item = itemRepository.findById(id).get();
        entityManager.lock(item, LockModeType.PESSIMISTIC_WRITE);
        item.decrease(quantity);
        itemRepository.save(item);
    }

    @Transactional
    public void decrease3(Long id, Long quantity) {
        Item item = entityManager.find(Item.class, id, LockModeType.PESSIMISTIC_WRITE);
        item.decrease(quantity);
        itemRepository.save(item);
    }

}
