package com.example.erplite.service;

import com.example.erplite.dto.inventory.InventoryResponse;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    InventoryResponse getByProductId(UUID productId);

    List<InventoryResponse> getAll();
}