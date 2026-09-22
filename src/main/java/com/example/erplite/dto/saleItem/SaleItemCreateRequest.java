package com.example.erplite.dto.saleItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class SaleItemCreateRequest {

    @NotNull(message = "Product id is required")
    private UUID productId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;
}