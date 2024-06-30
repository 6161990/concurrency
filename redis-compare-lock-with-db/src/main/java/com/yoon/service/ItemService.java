package com.yoon.service;

import com.yoon.aop.RedLock;
import com.yoon.domain.Item;
import com.yoon.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public  void decrease(Long id, Long  quantity){
        Item item = itemRepository.findById(id).orElseThrow();

        item.decrease(quantity);

        itemRepository.save(item);
    }

    @Transactional
    @RedLock(key = "#id")
    public void decreaseAop(Long id, Long quantity) {
        Item item = itemRepository.findById(id).orElseThrow();
        item.decrease(quantity);
        itemRepository.save(item);
    }
}
