package com.example.erplite.repository;

import com.example.erplite.entity.StockMovement;
import com.example.erplite.enums.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StockMovementRepository
        extends JpaRepository<StockMovement, UUID> {

    List<StockMovement> findByProductId(UUID productId);

    List<StockMovement> findByMovementType(MovementType movementType);

    List<StockMovement> findByProductIdOrderByCreatedAtDesc(UUID productId);
}