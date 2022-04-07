package com.yummy.mydelivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @OneToMany
    @JoinColumn(name = "ORDERDETAIL_ID")
    private List<OrderFoods> orderFoods ;

    @Column
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    public OrderDetail(String restaurantName, List<OrderFoods> orderFoods, int totalPrice, int deliveryFee) {
        this.restaurantName = restaurantName;
        this.orderFoods = orderFoods;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
    }

}
