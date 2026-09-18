package com.example.erplite.dto.inventory;

import com.example.erplite.enums.MovementType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class StockMovementCreateRequest {

    @NotNull(message = "Product id is required")
    private UUID productId;

    @NotNull(message = "Movement type is required")
    private MovementType movementType;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Unit cost is required")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Unit cost must be greater than 0")
    private BigDecimal unitCost;

    private UUID referenceId;
}