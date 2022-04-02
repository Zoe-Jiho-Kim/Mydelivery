package com.yummy.mydelivery.service;

import com.yummy.mydelivery.dto.FoodDto;
import com.yummy.mydelivery.model.Food;
import com.yummy.mydelivery.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;



    public Food registerFood(FoodDto foodDto) {
        Long restaurantId = foodDto.getRestarantId();
        String name = foodDto.getName();
        Long price = foodDto.getPrice();

        //메뉴 중복 확인
        Optional<Food> found = foodRepository.findByName(name);

        if (found.isPresent()){
            throw new IllegalArgumentException("중복된 메뉴가 존재합니다.");
        }

        if (100>price || 1000000<price){
            throw new IllegalArgumentException("100원에서 1,000,000원사이의 값을 입력해주세요.");
        } else if (price%100!=0){
            throw new IllegalArgumentException("100원 단위로 입력해주세요.");
        }
        Food food = new Food(foodDto, restaurantId);
        foodRepository.save(food);
        return food;
    }

    public List<Food> getMenu(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
