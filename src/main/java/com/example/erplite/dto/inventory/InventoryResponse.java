package com.example.erplite.dto.inventory;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InventoryResponse {

    private UUID id;

    private UUID productId;

    private String productName;

    private String barcode;

    private Integer quantity;

    private BigDecimal averageCost;

    private BigDecimal lastPurchasePrice;

    private LocalDateTime updatedAt;
}