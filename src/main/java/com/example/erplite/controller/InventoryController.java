package com.example.erplite.controller;

import com.example.erplite.dto.inventory.InventoryResponse;
import com.example.erplite.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<InventoryResponse> getAll() {
        return inventoryService.getAll();
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public InventoryResponse getByProductId(
            @PathVariable UUID productId) {

        return inventoryService.getByProductId(productId);
    }
}