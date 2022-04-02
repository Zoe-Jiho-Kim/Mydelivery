package com.yummy.mydelivery.model;

import com.yummy.mydelivery.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long minOrderPrice;

    @Column(nullable = false)
    private Long deliveryFee;

    public Restaurant(String name, Long minOrderPrice, Long deliveryFee) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }

    public Restaurant(RestaurantDto restaurantDto) {
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }


}
