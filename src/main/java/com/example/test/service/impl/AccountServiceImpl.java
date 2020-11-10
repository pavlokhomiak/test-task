package com.example.test.service.impl;

import com.example.test.annotations.LogMethod;
import com.example.test.model.Account;
import com.example.test.model.Order;
import com.example.test.repository.AccountRepository;
import com.example.test.service.AccountService;
import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"accountCache"})
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @LogMethod
    @CachePut(key = "#account")
    @Override
    public Account add(Account account) {
        return accountRepository.save(account);
    }

    @LogMethod
    @Override
    public Account getById(Long id) {
        return accountRepository.getOne(id);
    }

    @LogMethod
    @Cacheable
    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @LogMethod
    @Override
    public List<Order> getOrders(Long id) {
        return accountRepository.getOne(id).getOrders();
    }

    @LogMethod
    @Override
    public Order getAccountOrder(Long accountId, Long orderId) {
        return accountRepository.getOne(accountId).getOrders().stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().get();
    }

    @LogMethod
    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
