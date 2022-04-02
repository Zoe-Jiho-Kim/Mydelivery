package com.yummy.mydelivery.controller;

import com.yummy.mydelivery.dto.RestaurantDto;
import com.yummy.mydelivery.model.Restaurant;
import com.yummy.mydelivery.repository.RestaurantRepository;
import com.yummy.mydelivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    //레스토랑 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.registerRestaurant(restaurantDto);
    }

//    @PostMapping("/restaurant/register")
//    public Restaurant registerRestaurant(@RequestBody RestaurantDto restaurantDto) {
//        Restaurant restaurant = new Restaurant(restaurantDto);
//        return restaurantRepository.save(restaurant);
//    }

    // 레스토랑 목록 조회
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant(){return restaurantRepository.findAll();}

}
