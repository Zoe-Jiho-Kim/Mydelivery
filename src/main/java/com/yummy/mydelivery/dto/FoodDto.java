package com.yummy.mydelivery.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FoodDto {
    private Long restarantId;
    private String name;
    private Long price;
}
