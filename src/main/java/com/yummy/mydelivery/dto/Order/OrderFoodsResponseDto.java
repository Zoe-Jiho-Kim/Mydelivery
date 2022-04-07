package com.yummy.mydelivery.dto.Order;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFoodsResponseDto {
    private String name;
    private int quantity;
    private int price;

    public OrderFoodsResponseDto(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

}

