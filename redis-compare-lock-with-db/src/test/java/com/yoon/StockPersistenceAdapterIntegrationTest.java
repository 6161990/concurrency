package com.yoon;

import com.yoon.facade.StockPersistenceAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class StockPersistenceAdapterIntegrationTest extends AbstractTestcontainers {

    @Autowired
    private StockPersistenceAdapter stockPersistenceAdapter;

    @Test
    void stock_store() {
        String key = "testKey";
        String value = "testValue";

        stockPersistenceAdapter.store(key, value);
        String result = stockPersistenceAdapter.find(key);

        assertEquals(value, result);
    }

    @Test
    void delete() {
        String key = "testKey";
        String value = "testValue";

        stockPersistenceAdapter.store(key, value);
        Boolean deleted = stockPersistenceAdapter.delete(key);
        String result = stockPersistenceAdapter.find(key);

        assertTrue(deleted);
        assertNull(result);
    }
}
