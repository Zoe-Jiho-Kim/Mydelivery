package com.yummy.mydelivery.service;

import com.yummy.mydelivery.dto.Restaurant.RestaurantDto;
import com.yummy.mydelivery.model.Restaurant;
import com.yummy.mydelivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant registerRestaurant(RestaurantDto restaurantDto) {
        String name = restaurantDto.getName();
        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        //레스토랑 중복 확인
        Optional<Restaurant> found = restaurantRepository.findByname(name);
//
        if (found.isPresent()){
            throw new IllegalArgumentException("중복된 레스토랑이 존재합니다.");
//            return "중복된 레스토랑이 존재합니다.";
        }

        //최소주문가격,배달비 유효성
        if (minOrderPrice%100!=0){
            throw new IllegalArgumentException("100원 단위로 입력해주세요.");
//            return "100원 단위로 입력해주세요.";
        } else if (1000>minOrderPrice || minOrderPrice>100000){
            throw new IllegalArgumentException("1,000원에서 100,000원사이의 값을 입력해주세요.");
//            return "1,000원에서 100,000원사이의 값을 입력해주세요.";
        } else if (deliveryFee%500!=0){
            throw new IllegalArgumentException("500원 단위로 입력해주세요.");
//            return "500원 단위로 입력해주세요.";
        } else if (0>deliveryFee || deliveryFee>10000){
            throw new IllegalArgumentException("0원에서 10,000원 사이의 값을 입력해주세요.");
//            return "0원에서 10,000원 사이의 값을 입력해주세요.";
        }
        Restaurant restaurant = new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
