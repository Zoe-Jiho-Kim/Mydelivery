package com.yummy.mydelivery.dto.Order;

import com.yummy.mydelivery.model.OrderFoods;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequestDto {
    public Long restaurantId;
    private List<OrderFoods> foods;
}
