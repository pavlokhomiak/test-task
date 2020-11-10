package com.example.test.model.dto;

import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private String productName;
    private int amount;
}
