package com.yoon.repository;

import com.yoon.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 편의상 Stock 엔티티를 사용하지만, 실무에서는 별도로 사용해야함.
 * */
public interface LockRepository extends JpaRepository<Stock, Long > {

    @Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
    void getLock(String key);

    @Query(value = "select release_lock(:key)", nativeQuery = true)
    void releaseLock(String key);
}
