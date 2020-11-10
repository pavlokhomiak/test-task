package com.example.test.controllers;

import com.example.test.mapper.AccountMapper;
import com.example.test.mapper.OrderMapper;
import com.example.test.mapper.UserMapper;
import com.example.test.model.dto.OrderRequest;
import com.example.test.model.dto.OrderResponse;
import com.example.test.model.dto.UserRequestDto;
import com.example.test.model.dto.UserResponseDto;
import com.example.test.service.AccountService;
import com.example.test.service.OrderService;
import com.example.test.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public UserController(UserService userService,
                          UserMapper userMapper,
                          OrderService orderService,
                          OrderMapper orderMapper,
                          AccountService accountService,
                          AccountMapper accountMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userMapper.toDto(userService.getById(id));
    }

    @PostMapping
    public void create(@RequestBody @Valid UserRequestDto dto) {
        userService.register(userMapper.toUser(dto));
    }

    @PutMapping("/{id}/admin/{admin}")
    public void updateAdmin(@PathVariable Long id,
                       @PathVariable boolean admin) {
        userService.updateAdmin(id, admin);
    }

    @PutMapping("/{id}/active/{active}")
    public void updateActive(@PathVariable Long id,
                       @PathVariable boolean active) {
        userService.updateActive(id, active);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/orders")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/orders/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {
        return orderMapper.toDto(orderService.getById(id));
    }

    @PutMapping("/orders/{id}")
    public void updateOrder(@RequestBody @Valid OrderRequest dto,
                       @PathVariable Long id) {
        orderService.update(orderMapper.toOrder(dto), id);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
    }

    @GetMapping("/orders/account/{id}")
    public List<OrderResponse> getAccountOrders(@PathVariable Long id) {
        return accountService.getOrders(id).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/orders/account/{id}")
    public void addOrder(@RequestBody @Valid OrderRequest dto,
                         @PathVariable Long id) {
        orderService.add(orderMapper.toOrder(dto), id);
    }

    @GetMapping("/orders/account/{accountId}/orders/{orderId}")
    public OrderResponse getAccountOrder(@PathVariable Long accountId,
                                         @PathVariable Long orderId) {
        return orderMapper.toDto(accountService.getAccountOrder(accountId, orderId));
    }

    @PutMapping("/orders/account/{accountId}/orders/{orderId}")
    public void updateAccountOrder(@RequestBody @Valid OrderRequest dto,
                                   @PathVariable Long accountId,
                                   @PathVariable Long orderId) {
        orderService.updateAccountOrder(orderMapper.toOrder(dto), accountId, orderId);
    }

    @DeleteMapping("/orders/account/{accountId}/orders/{orderId}")
    public void deleteAccountOrder(@PathVariable Long accountId,
                                   @PathVariable Long orderId) {
        orderService.deleteAccountOrder(accountId, orderId);
    }
}
