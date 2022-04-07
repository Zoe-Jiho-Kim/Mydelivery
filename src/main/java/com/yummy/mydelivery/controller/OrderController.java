package com.yummy.mydelivery.controller;

import com.yummy.mydelivery.dto.Order.OrderDetailDto;
import com.yummy.mydelivery.dto.Order.OrderRequestDto;
import com.yummy.mydelivery.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/request")
    public OrderDetailDto createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderDetailDto> getOrder(){
        return orderService.showOrders();
    }
}
