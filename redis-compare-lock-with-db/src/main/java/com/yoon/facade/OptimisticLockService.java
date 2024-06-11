package com.yoon.facade;

import com.yoon.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptimisticLockService {

    private final ItemRepository itemRepository;

    public void decrease(Long id, Long quantity) {
        itemRepository.findById(id).map(value -> {
            value.decrease(quantity);
            return value;
        }).ifPresent(itemRepository::save);
    }

}
