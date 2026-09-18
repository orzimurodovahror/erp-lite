package com.example.erplite.dto.inventory;

import com.example.erplite.enums.MovementType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StockMovementResponse {
    private UUID id;
    private UUID productId;
    private String productName;
    private String barcode;
    private MovementType movementType;
    private Integer quantity;
    private BigDecimal unitCost;
    private UUID referenceId;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private String createdByUsername;
}