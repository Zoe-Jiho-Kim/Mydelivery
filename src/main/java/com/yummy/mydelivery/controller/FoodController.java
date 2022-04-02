package com.yummy.mydelivery.controller;

import com.yummy.mydelivery.dto.FoodDto;
import com.yummy.mydelivery.model.Food;
import com.yummy.mydelivery.repository.FoodRepository;
import com.yummy.mydelivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodService foodService;

    //메뉴 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public Food registerFood(@RequestBody FoodDto foodDto) {
        return foodService.registerFood(foodDto);
    }

    // 레스토랑 목록 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getMenu(@PathVariable Long restaurantId){return foodService.getMenu(restaurantId);}
}
