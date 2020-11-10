package com.example.test.mapper;

import com.example.test.model.Account;
import com.example.test.model.dto.AccountResponse;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    private final OrderMapper orderMapper;

    public AccountMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public AccountResponse toDto(Account account) {
        AccountResponse dto = new AccountResponse();
        dto.setId(account.getId());
        dto.setOrders(account.getOrders().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList()));
        return dto;
    }
}
