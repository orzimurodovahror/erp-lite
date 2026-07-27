package com.example.erplite.entity;

import com.example.erplite.enums.ProductStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;




@Entity
@Table(name = "products")
public class Product extends Base {


    @Column(name = "barcode", nullable = false, unique = true)
    private String barcode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "buy_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal buyPrice;

    @Column(name = "sell_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal sellPrice;

    @Column(name = "min_stock", nullable = false)
    private Integer minStock;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;





}
