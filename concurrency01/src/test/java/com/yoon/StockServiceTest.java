package com.yoon;

import com.yoon.domain.Stock;
import com.yoon.repository.StockRepository;
import com.yoon.service.StockService;
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

        Stock stock = stockRepository.findById(PRODUCT_ID).orElseThrow();

        assertThat(stock.getQuantity()).isEqualTo(99L);
    }

    @Test
    void concurrency_100() throws InterruptedException {
        /**
         * ExecutorService : 비동기로 실행하는 작업을 단순하하여 사용할 수 있게 해주는 자바 API
         * CountDownLatch : 다른 스레드에서 실행중인 작업이 완료될 때까지 대기할 수 있도록 도와주는 클래스
         * */
        int totalThread = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(totalThread);

        for (int i = 0; i < totalThread; i++) {
            executorService.submit(() -> {
               try {
                   sut.decrease(PRODUCT_ID, 1L);
               }finally {
                   latch.countDown();
               }
            });
        }

        latch.await();

        Stock stock = stockRepository.findById(PRODUCT_ID).orElseThrow();
        assertThat(stock.getQuantity()).isEqualTo(0L);
    }
}
