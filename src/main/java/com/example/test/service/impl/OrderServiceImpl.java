package com.example.test.service.impl;

import com.example.test.annotations.LogMethod;
import com.example.test.model.Account;
import com.example.test.model.Order;
import com.example.test.repository.OrderRepository;
import com.example.test.service.AccountService;
import com.example.test.service.OrderService;
import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"orderCache"})
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AccountService accountService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            AccountService accountService) {
        this.orderRepository = orderRepository;
        this.accountService = accountService;
    }

    @LogMethod
    @CachePut(key = "#order")
    @Override
    public Order add(Order order, Long id) {
        Order orderFromDb = orderRepository.save(order);
        List<Account> a = accountService.getAll();
        Account accountFromDb = accountService.getById(id);
        accountFromDb.getOrders().add(orderFromDb);
        accountService.add(accountFromDb);
        return orderFromDb;
    }

    @LogMethod
    @Override
    public Order getById(Long id) {
        return orderRepository.getOne(id);
    }

    @LogMethod
    @Cacheable
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @LogMethod
    @Override
    public void update(Order order, Long id) {
        Order orderFromDb = orderRepository.getOne(id);
        orderFromDb.setProductName(order.getProductName());
        orderFromDb.setAmount(order.getAmount());
        orderRepository.save(orderFromDb);
    }

    @LogMethod
    @Override
    public void updateAccountOrder(Order order, Long accountId, Long orderId) {
        Order orderFromDb = accountService.getAccountOrder(accountId, orderId);
        orderFromDb.setProductName(order.getProductName());
        orderFromDb.setAmount(order.getAmount());
        orderRepository.save(orderFromDb);
    }

    @LogMethod
    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @LogMethod
    @Override
    public void deleteAccountOrder(Long accountId, Long orderId) {
        Order orderFromDb = getById(orderId);
        Account accountFromDb = accountService.getById(accountId);
        accountFromDb.getOrders().remove(orderFromDb);
        accountService.add(accountFromDb);
        orderRepository.deleteById(orderId);
    }
}
