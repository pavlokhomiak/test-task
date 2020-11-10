package com.example.test.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class AccountResponse {
    private Long id;
    private List<OrderResponse> orders;
}
