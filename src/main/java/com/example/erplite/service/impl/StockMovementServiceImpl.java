package com.example.erplite.service.impl;

import com.example.erplite.dto.inventory.StockMovementCreateRequest;
import com.example.erplite.dto.inventory.StockMovementResponse;
import com.example.erplite.entity.Inventory;
import com.example.erplite.entity.Product;
import com.example.erplite.entity.StockMovement;
import com.example.erplite.entity.User;
import com.example.erplite.enums.MovementType;
import com.example.erplite.exp.ProductNotFoundException;
import com.example.erplite.repository.InventoryRepository;
import com.example.erplite.repository.ProductRepository;
import com.example.erplite.repository.StockMovementRepository;
import com.example.erplite.repository.UserRepository;
import com.example.erplite.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl
        implements StockMovementService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final StockMovementRepository stockMovementRepository;
    private final UserRepository userRepository;

    // ...

    @Override
    @Transactional
    public StockMovementResponse create(StockMovementCreateRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        Inventory inventory = inventoryRepository
                .findByProductId(product.getId())
                .orElseGet(() -> createInitialInventory(product));

        int quantity = request.getQuantity();

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than 0"
            );
        }

        if (request.getMovementType() == MovementType.SALE) {

            if (inventory.getQuantity() < quantity) {
                throw new IllegalArgumentException(
                        "Not enough stock"
                );
            }

            inventory.setQuantity(
                    inventory.getQuantity() - quantity
            );

        } else {

            inventory.setQuantity(
                    inventory.getQuantity() + quantity
            );
        }

        inventoryRepository.save(inventory);


        // CURRENT USER
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));


        // STOCK MOVEMENT
        StockMovement movement = new StockMovement();

        movement.setProduct(product);
        movement.setMovementType(request.getMovementType());
        movement.setQuantity(quantity);
        movement.setUnitCost(request.getUnitCost());
        movement.setReferenceId(request.getReferenceId());
        movement.setCreatedAt(LocalDateTime.now());

        movement.setCreatedBy(user);

        StockMovement savedMovement =
                stockMovementRepository.save(movement);

        return toResponse(savedMovement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockMovementResponse> getAll() {

        return stockMovementRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<StockMovementResponse> getByProductId(UUID productId) {

            return stockMovementRepository.findByProductId(productId)
                    .stream()
                    .map(this::toResponse)
                    .toList();

    }

    private StockMovementResponse toResponse(
            StockMovement movement) {

        StockMovementResponse response =
                new StockMovementResponse();

        response.setId(movement.getId());
        response.setProductId(
                movement.getProduct().getId()
        );
        response.setProductName(
                movement.getProduct().getName()
        );
        response.setBarcode(
                movement.getProduct().getBarcode()
        );
        response.setMovementType(
                movement.getMovementType()
        );
        response.setQuantity(
                movement.getQuantity()
        );
        response.setUnitCost(
                movement.getUnitCost()
        );
        response.setReferenceId(
                movement.getReferenceId()
        );
        response.setCreatedAt(
                movement.getCreatedAt()
        );
        response.setCreatedBy(movement.getCreatedBy().getId());
        response.setCreatedByUsername(movement.getCreatedBy().getUsername());

        return response;
    }
    private Inventory createInitialInventory(Product product) {

        Inventory inventory = new Inventory();

        inventory.setProduct(product);
        inventory.setQuantity(0);
        inventory.setAverageCost(BigDecimal.ZERO);
        inventory.setLastPurchasePrice(BigDecimal.ZERO);

        return inventoryRepository.save(inventory);
    }
}