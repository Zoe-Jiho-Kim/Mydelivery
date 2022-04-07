package com.yummy.mydelivery.repository;

import com.yummy.mydelivery.model.OrderDetail;
import com.yummy.mydelivery.model.OrderFoods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFoodsRepository extends JpaRepository<OrderFoods, Long> {

    List<OrderFoods> findOrderFoodsByOrderDetail(OrderDetail orderDetail);
}
