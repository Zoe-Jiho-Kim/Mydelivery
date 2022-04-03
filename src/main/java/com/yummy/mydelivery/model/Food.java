package com.yummy.mydelivery.model;

import com.yummy.mydelivery.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Food {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(name = "restaurant_id")
    private Long restaurantId;


    public Food(Long restaurantId, String name, Long price) {
        this.restaurantId =restaurantId;
        this.name = name;
        this.price = price;
    }

    public Food(FoodDto foodDto, Long restaurantId) {
        this.restaurantId = restaurantId;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }

}
