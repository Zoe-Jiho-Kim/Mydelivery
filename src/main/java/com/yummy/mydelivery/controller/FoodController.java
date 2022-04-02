package com.yummy.mydelivery.controller;

import com.yummy.mydelivery.dto.FoodDto;
import com.yummy.mydelivery.model.Food;
import com.yummy.mydelivery.repository.FoodRepository;
import com.yummy.mydelivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodService foodService;

    //메뉴 등록
    @PostMapping("/restaurant/register")
    public Food registerFood(@RequestBody FoodDto foodDto) {
        return foodService.registerFood(foodDto);
    }

}
