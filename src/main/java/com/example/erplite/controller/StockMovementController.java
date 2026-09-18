package com.example.erplite.controller;

import com.example.erplite.dto.inventory.StockMovementCreateRequest;
import com.example.erplite.dto.inventory.StockMovementResponse;
import com.example.erplite.service.StockMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stock-movements")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StockMovementResponse create(
            @Valid @RequestBody StockMovementCreateRequest request) {
        return stockMovementService.create(request);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<StockMovementResponse> getAll() {
        return stockMovementService.getAll();
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<StockMovementResponse> getByProductId(
            @PathVariable UUID productId) {

        return stockMovementService.getByProductId(productId);
    }
}