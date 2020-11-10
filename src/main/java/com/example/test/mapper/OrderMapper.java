package com.example.test.mapper;

import com.example.test.model.Order;
import com.example.test.model.dto.OrderRequest;
import com.example.test.model.dto.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest dto) {
        Order order = new Order();
        order.setProductName(dto.getProductName());
        order.setAmount(dto.getAmount());
        return order;
    }

    public OrderResponse toDto(Order order) {
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setAmount(order.getAmount());
        dto.setProductName(order.getProductName());
        return dto;
    }
}
