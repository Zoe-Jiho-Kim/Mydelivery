package com.yummy.mydelivery.repository;

import com.yummy.mydelivery.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);
    List<Food> findAllByRestaurantId(Long restaurantId);
}
