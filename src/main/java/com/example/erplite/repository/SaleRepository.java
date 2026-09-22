package com.example.erplite.repository;

import com.example.erplite.entity.Sale;
import com.example.erplite.enums.SaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {

    boolean existsByReceiptNo(String receiptNo);

    Optional<Sale> findByReceiptNo(String receiptNo);

    List<Sale> findByCashierId(UUID cashierId);

    List<Sale> findByStatus(SaleStatus status);
}