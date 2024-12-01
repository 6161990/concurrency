package com.yoon.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private Long id;

    private Long quantity;

    private Long version;

    public void decrease(Long quantity) {
        if(this.quantity - quantity < 0){
            throw new RuntimeException("재고는 0 개 미만이 될 수 없습니다.");
        }

        this.quantity -= quantity;
    }
}
