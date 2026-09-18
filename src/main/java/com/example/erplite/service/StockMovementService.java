package com.example.erplite.service;

import com.example.erplite.dto.inventory.StockMovementCreateRequest;
import com.example.erplite.dto.inventory.StockMovementResponse;

import java.util.List;
import java.util.UUID;

public interface StockMovementService {

    StockMovementResponse create(StockMovementCreateRequest request);

    List<StockMovementResponse> getAll();

    List<StockMovementResponse> getByProductId(UUID productId);
}