package com.yummy.mydelivery.service;

import com.yummy.mydelivery.dto.Order.OrderDetailDto;
import com.yummy.mydelivery.dto.Order.OrderFoodsResponseDto;
import com.yummy.mydelivery.dto.Order.OrderRequestDto;
import com.yummy.mydelivery.model.Food;
import com.yummy.mydelivery.model.OrderDetail;
import com.yummy.mydelivery.model.OrderFoods;
import com.yummy.mydelivery.model.Restaurant;
import com.yummy.mydelivery.repository.FoodRepository;
import com.yummy.mydelivery.repository.OrderFoodsRepository;
import com.yummy.mydelivery.repository.OrderRepository;
import com.yummy.mydelivery.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final OrderFoodsRepository orderFoodsRepository;

    public OrderService(
            RestaurantRepository restaurantRepository,
            FoodRepository foodRepository,
            OrderRepository orderRepository,
            OrderFoodsRepository orderFoodsRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.orderRepository = orderRepository;
        this.orderFoodsRepository = orderFoodsRepository;
    }

    @Transactional
    public OrderDetailDto createOrder(OrderRequestDto orderRequestDtoList) {
        Restaurant restaurant = restaurantRepository.findById(orderRequestDtoList.getRestaurantId()).orElse(null);
        if (restaurant == null) {
            throw new NullPointerException("해당 음식점이 없습니다.");
        }

        List<OrderFoodsResponseDto> orderFoodsResponseDtoList = new ArrayList<>();

        List<OrderFoods> orderList = new ArrayList<>();

        int totalPrice = 0;

        for (OrderFoods orderRequestDto : orderRequestDtoList.getFoods()) {

            int quantity = orderRequestDto.getQuantity();

            if (quantity < 0 || 100 < quantity) {
                throw new IllegalArgumentException("음식은 1개 이상 100개 이하로 주문 할 수 있습니다.");
            }

            Food food = foodRepository.findById(orderRequestDto.getId())
                    .orElseThrow(() -> new NullPointerException("해당음식이 없습니다."));

            OrderFoods orderFoods = new OrderFoods(food, quantity);
            orderFoodsRepository.save(orderFoods);

            orderList.add(orderFoods);

            totalPrice += food.getPrice() * quantity;

            OrderFoodsResponseDto orderFoodsResponseDto = new OrderFoodsResponseDto(
                    orderFoods.getFood().getName(),
                    quantity,
                    orderFoods.getFood().getPrice() * quantity);

            orderFoodsResponseDtoList.add(orderFoodsResponseDto);
        }
        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소주문금액 이상 주문해주세요!");
        }
        // 음식점의 배달비를 뺴냄
        int deliveryFee = restaurant.getDeliveryFee();
        // 총가격에 배달비를 더해줌
        totalPrice += deliveryFee;
        // 주문을 저장해줌
        OrderDetail orderDetail = new OrderDetail(
                restaurant.getName(),
                orderList, totalPrice,
                deliveryFee
        );
        orderRepository.save(orderDetail);

        for (OrderFoods foods : orderList){
            foods.setOrderDetail(orderDetail);
        }
        // 고객에게 주문정보를 보여줌
        return new OrderDetailDto(
                orderDetail,
                orderFoodsResponseDtoList,
                deliveryFee
        );
    }

    @Transactional
    public List<OrderDetailDto> showOrders() {

        List<OrderDetailDto> orderFoodsResponseDtoList =new ArrayList<>();

        List<OrderFoodsResponseDto> ordersResponse = new ArrayList<>();

        List<OrderDetail> orderDetailList = orderRepository.findAll();

        for (OrderDetail orderDetail : orderDetailList){
            // 배달비를 빼냄
            int deliveryFee = orderDetail.getDeliveryFee();
            // 주문정보중에 음식정보를 빼냄
            List<OrderFoods> orderFoodsList  = orderFoodsRepository.findOrderFoodsByOrderDetail(orderDetail);
            // 빼낸 음식정보리스트에서 음식정보를 하나씩 빼냄
            for (OrderFoods orderFoods : orderFoodsList){
                // 빼낸 음식정보를 보여줄 정보로 바꿈
                OrderFoodsResponseDto orderFoodsResponseDto = new OrderFoodsResponseDto(
                        orderFoods.getFood().getName(),
                        orderFoods.getQuantity(),
                        orderFoods.getFood().getPrice()*orderFoods.getQuantity());
                //보여줄 정보로 바꾼 음식 하나씩 보여줄 리스트에 넣어줌
                ordersResponse.add(orderFoodsResponseDto);
            }
            //주문 정보를 하나씩 만들어줌
            OrderDetailDto ordersResponseDto =  new OrderDetailDto(orderDetail,ordersResponse,deliveryFee);
            //하나씩 만든 주문정보들을 주문정보들의 리스트에 넣어줌
            orderFoodsResponseDtoList.add(ordersResponseDto);
        }
        //보여줌
        return orderFoodsResponseDtoList;

    }
}


