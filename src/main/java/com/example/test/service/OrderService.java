package com.example.test.service;

import com.example.test.model.Order;
import java.util.List;

public interface OrderService {
    Order add(Order order, Long id);

    Order getById(Long id);

    List<Order> getAll();

    void update(Order order, Long id);

    void updateAccountOrder(Order order, Long accountId, Long orderId);

    void delete(Long id);

    void deleteAccountOrder(Long accountId, Long orderId);
}
