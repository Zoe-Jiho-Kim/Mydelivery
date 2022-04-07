package com.yummy.mydelivery.model;

import com.yummy.mydelivery.dto.Food.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Food {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    public Food(FoodDto request, Restaurant restaurant) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.restaurant = restaurant;
    }

}
