package com.example.erplite.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {

    @NotBlank(message = "Barcode is required")
    private String barcode;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Buy price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Buy price must be greater than 0")
    private BigDecimal buyPrice;

    @NotNull(message = "Sell price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Sell price must be greater than 0")
    private BigDecimal sellPrice;

    @NotNull(message = "Minimum stock is required")
    private Integer minStock;
}