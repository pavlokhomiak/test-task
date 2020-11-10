package com.example.test.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
    @NotNull
    @NotEmpty
    private String productName;
    @Min(1)
    private int amount;
}
