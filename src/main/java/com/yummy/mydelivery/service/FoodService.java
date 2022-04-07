package com.yummy.mydelivery.service;

import com.yummy.mydelivery.dto.Food.FoodDto;
import com.yummy.mydelivery.model.Food;
import com.yummy.mydelivery.model.Restaurant;
import com.yummy.mydelivery.repository.FoodRepository;
import com.yummy.mydelivery.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public void registerFood(Long restaurantId, List<FoodDto> foodDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant == null) {
            throw new NullPointerException("해당 음식점이 없습니다.");
        }
        for (FoodDto requestDto : foodDto) {
            //메뉴 중복 확인
            Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant, requestDto.getName());

            if (found.isPresent()) {
                throw new IllegalArgumentException("중복된 메뉴가 존재합니다.");
            } else if (100 > requestDto.getPrice() || 1000000 < requestDto.getPrice()) {
                throw new IllegalArgumentException("100원에서 1,000,000원사이의 값을 입력해주세요.");
            } else if (requestDto.getPrice() % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로 입력해주세요.");
            } else {
                Food food = new Food(requestDto, restaurant);
                foodRepository.save(food);
            }
        }
    }
}

