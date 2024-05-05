package com.yoon.repository;

import com.yoon.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Item, Long> {

}
