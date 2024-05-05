package com.yoon.service;

import com.yoon.domain.Item;
import com.yoon.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private final StockRepository stockRepository;

    public ItemService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public  void decrease(Long id, Long  quantity){
        Item item = stockRepository.findById(id).orElseThrow();

        item.decrease(quantity);

        stockRepository.saveAndFlush(item);
    }
}
