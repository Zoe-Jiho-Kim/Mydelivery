package com.yummy.mydelivery.controller;

import com.yummy.mydelivery.dto.FoodDto;
import com.yummy.mydelivery.model.Food;
import com.yummy.mydelivery.repository.FoodRepository;
import com.yummy.mydelivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodService foodService;

    //메뉴 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDto) {
       foodService.registerFood(restaurantId, foodDto);
    }

    // 레스토랑 목록 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getMenu(@PathVariable Long restaurantId){return foodService.getMenu(restaurantId);}
}
