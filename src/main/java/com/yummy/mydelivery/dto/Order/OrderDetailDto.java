package com.yummy.mydelivery.dto.Order;

import com.yummy.mydelivery.model.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDetailDto {
    private String restaurantName;
    private List<OrderFoodsResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderDetailDto(OrderDetail orderDetail, List<OrderFoodsResponseDto> orderFoodsResponseDtoList, int deliveryFee) {
        this.restaurantName = orderDetail.getRestaurantName();
        this.foods = orderFoodsResponseDtoList;
        this.deliveryFee = deliveryFee;
        this.totalPrice = orderDetail.getTotalPrice();
    }

}
