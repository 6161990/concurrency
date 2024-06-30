package com.yoon.controller;

import com.yoon.facade.LockService;
import com.yoon.facade.RedissonLockStockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DecreaseController {
    private final LockService lockService;
    private final RedissonLockStockFacade redissonLockStockFacade;

    @PostMapping("/decrease")
    public ResponseEntity<HttpStatus> decrease2() {
        lockService.decrease3(1L, 1L);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/redission/decrease")
    public ResponseEntity<HttpStatus> decrease(){
        redissonLockStockFacade.decrease(1L, 1L);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
