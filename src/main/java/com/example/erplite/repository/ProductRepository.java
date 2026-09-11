package com.example.erplite.repository;

import com.example.erplite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    boolean existsByBarcode(String barcode);

    Optional<Product> findByBarcode(String barcode);

}