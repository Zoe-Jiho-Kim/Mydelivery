package com.yummy.mydelivery.repository;

import com.yummy.mydelivery.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail, Long> {
}
