package com.yummy.mydelivery.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestaurantDto {
    private String name;
    private Long minOrderPrice;
    private Long deliveryFee;
}
