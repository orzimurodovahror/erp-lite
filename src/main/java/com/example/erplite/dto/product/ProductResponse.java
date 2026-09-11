package com.example.erplite.dto.product;

import com.example.erplite.enums.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductResponse {

    private UUID id;

    private String barcode;

    private String name;

    private String description;

    private BigDecimal buyPrice;

    private BigDecimal sellPrice;

    private Integer minStock;

    private ProductStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}