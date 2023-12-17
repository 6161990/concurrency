package com.yoon;

import com.yoon.domain.Stock;
import com.yoon.facade.LettuceLockStockFacade;
import com.yoon.facade.RedissonLockStockFacade;
import com.yoon.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedissonLockStockFacadeTest {

    private static final long PRODUCT_ID = 1L;

    @Autowired
    private RedissonLockStockFacade sut;

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
    void concurrency_100() throws InterruptedException {
        int totalThread = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(totalThread);

        for (int i = 0; i < totalThread; i++) {
            executorService.submit(() -> {
               try {
                   sut.decrease(PRODUCT_ID, 1L);
               } finally {
                   latch.countDown();
               }
            });
        }

        latch.await();

        Stock stock = stockRepository.findById(PRODUCT_ID).orElseThrow();
        assertThat(stock.getQuantity()).isEqualTo(0L);
    }
}
