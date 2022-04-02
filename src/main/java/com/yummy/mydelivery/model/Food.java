package com.yummy.mydelivery.model;

import com.yummy.mydelivery.dto.FoodDto;
import com.yummy.mydelivery.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long price;

//    @ManyToOne
//    @JoinColumn(name = "restaurantId")
//    private Restaurant restaurant;
//
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
