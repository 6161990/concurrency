package com.yoon.service;

import com.yoon.domain.Item;
import com.yoon.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public  void decrease(Long id, Long  quantity){
        Item item = itemRepository.findById(id).orElseThrow();

        item.decrease(quantity);

        itemRepository.saveAndFlush(item);
    }
}
