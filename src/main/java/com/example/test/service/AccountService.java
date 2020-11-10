package com.example.test.service;

import com.example.test.model.Account;
import com.example.test.model.Order;
import java.util.List;

public interface AccountService {
    Account add(Account account);

    Account getById(Long id);

    List<Account> getAll();

    List<Order> getOrders(Long id);

    Order getAccountOrder(Long accountId, Long orderId);

    void delete(Long id);
}
