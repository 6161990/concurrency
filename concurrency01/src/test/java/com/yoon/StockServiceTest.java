package com.yoon;

import com.yoon.domain.Stock;
import com.yoon.repository.StockRepository;
import com.yoon.service.StockService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StockServiceTest {

    private static final long PRODUCT_ID = 1L;

    @Autowired
    private StockService sut;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        stockRepository.saveAndFlush(new Stock(PRODUCT_ID, 100L));
    }

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
    }

    @Test
    void decrease() {
        sut.decrease(PRODUCT_ID, 1L);

        Stock stock = stockRepository.findById( PRODUCT_ID).orElseThrow();

        assertThat(stock.getQuantity()).isEqualTo(99L);
    }
}
