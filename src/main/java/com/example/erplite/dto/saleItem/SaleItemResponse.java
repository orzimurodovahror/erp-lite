package com.example.erplite.dto.saleItem;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class SaleItemResponse {

    private UUID id;

    private UUID productId;

    private String productName;

    private String barcode;

    private Integer quantity;

    private BigDecimal buyPrice;

    private BigDecimal sellPrice;

    private BigDecimal totalAmount;

    private BigDecimal profit;
}