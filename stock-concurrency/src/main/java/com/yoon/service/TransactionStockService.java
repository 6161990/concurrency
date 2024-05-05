package com.yoon.service;

/**
 * Transactional 이 실행되는 방식을 설명하기 위한 클래스
 * */
public class TransactionStockService {

    private  StockService stockService;

    public TransactionStockService(StockService stockService) {
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity){
        startTransaction();

        stockService.decrease(id, quantity);

        endTransaction();
    }

    private void startTransaction() {
        System.out.println("start transaction");
    }

    private void endTransaction() {
        System.out.println("end transaction");
    }
}
