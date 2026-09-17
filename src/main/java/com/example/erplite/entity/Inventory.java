package com.example.erplite.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "average_cost", nullable = false, precision = 19, scale = 2)
    private BigDecimal averageCost;

    @Column(name = "last_purchase_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal lastPurchasePrice;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
