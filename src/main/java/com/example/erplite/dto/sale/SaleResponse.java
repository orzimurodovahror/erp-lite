package com.example.erplite.dto.sale;

import com.example.erplite.dto.saleItem.SaleItemResponse;
import com.example.erplite.enums.SaleStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class SaleResponse {

    private UUID id;

    private String receiptNo;

    private UUID cashierId;

    private String cashierUsername;

    private BigDecimal totalAmount;

    private BigDecimal totalCost;

    private BigDecimal totalProfit;

    private SaleStatus status;

    private LocalDateTime createdAt;

    private List<SaleItemResponse> items;
}