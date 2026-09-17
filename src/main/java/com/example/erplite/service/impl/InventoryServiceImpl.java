package com.example.erplite.service.impl;

import com.example.erplite.dto.inventory.InventoryResponse;
import com.example.erplite.entity.Inventory;
import com.example.erplite.exp.InventoryNotFoundException;
import com.example.erplite.repository.InventoryRepository;
import com.example.erplite.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public InventoryResponse getByProductId(UUID productId) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found"));

        return toResponse(inventory);
    }

    @Override
    public List<InventoryResponse> getAll() {

        return inventoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private InventoryResponse toResponse(Inventory inventory) {

        InventoryResponse response = new InventoryResponse();

        response.setId(inventory.getId());
        response.setProductId(inventory.getProduct().getId());
        response.setProductName(inventory.getProduct().getName());
        response.setBarcode(inventory.getProduct().getBarcode());
        response.setQuantity(inventory.getQuantity());
        response.setAverageCost(inventory.getAverageCost());
        response.setLastPurchasePrice(inventory.getLastPurchasePrice());
        response.setUpdatedAt(inventory.getUpdatedAt());

        return response;
    }
}