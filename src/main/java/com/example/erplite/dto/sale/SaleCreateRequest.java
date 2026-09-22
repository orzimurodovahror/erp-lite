package com.example.erplite.dto.sale;

import com.example.erplite.dto.saleItem.SaleItemCreateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class SaleCreateRequest {

    private String paymentType;

    @NotEmpty(message = "Sale items are required")
    @Valid
    private List<SaleItemCreateRequest> items;

}