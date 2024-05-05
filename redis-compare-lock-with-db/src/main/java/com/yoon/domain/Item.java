package com.yoon.domain;

import javax.persistence.*;

@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long quantity;

    @Version
    private Long version;

    public Item() {
    }

    public Item(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void decrease(Long quantity) {
        if(this.quantity - quantity < 0){
            throw new RuntimeException("재고는 0 개 미만이 될 수 없습니다.");
        }

        this.quantity -= quantity;
    }
}