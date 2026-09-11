package com.example.erplite.service;

import com.example.erplite.dto.product.ProductCreateRequest;
import com.example.erplite.dto.product.ProductResponse;
import com.example.erplite.dto.product.ProductUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse create(ProductCreateRequest request);

    ProductResponse update(UUID id, ProductUpdateRequest request);

    ProductResponse getById(UUID id);

    List<ProductResponse> getAll();

    void delete(UUID id);
}