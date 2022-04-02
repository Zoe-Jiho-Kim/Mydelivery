package com.yummy.mydelivery.repository;

import com.yummy.mydelivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByname(String name);
    List<Restaurant> findAll();
}
