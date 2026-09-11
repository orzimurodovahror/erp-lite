package com.example.erplite.service.impl;

import com.example.erplite.dto.product.ProductCreateRequest;
import com.example.erplite.dto.product.ProductResponse;
import com.example.erplite.dto.product.ProductUpdateRequest;
import com.example.erplite.entity.Product;
import com.example.erplite.enums.ProductStatus;
import com.example.erplite.exp.BarcodeAlreadyExistsException;
import com.example.erplite.exp.ProductNotFoundException;
import com.example.erplite.repository.ProductRepository;
import com.example.erplite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductCreateRequest request) {

        if (productRepository.existsByBarcode(request.getBarcode())) {
            throw new BarcodeAlreadyExistsException("Barcode already exists");
        }

        Product product = new Product();
        product.setBarcode(request.getBarcode());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBuyPrice(request.getBuyPrice());
        product.setSellPrice(request.getSellPrice());
        product.setMinStock(request.getMinStock());
        product.setStatus(ProductStatus.ACTIVE);

        Product savedProduct = productRepository.save(product);

        return toResponse(savedProduct);
    }

    @Override
    public ProductResponse update(UUID id, ProductUpdateRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        if (!product.getBarcode().equals(request.getBarcode())
                && productRepository.existsByBarcode(request.getBarcode())) {

            throw new BarcodeAlreadyExistsException("Barcode already exists");
        }

        product.setBarcode(request.getBarcode());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBuyPrice(request.getBuyPrice());
        product.setSellPrice(request.getSellPrice());
        product.setMinStock(request.getMinStock());

        Product updatedProduct = productRepository.save(product);

        return toResponse(updatedProduct);
    }

    @Override
    public ProductResponse getById(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        return toResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {

        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
    @Override
    public void delete(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        productRepository.delete(product);
    }

    private ProductResponse toResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setBarcode(product.getBarcode());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setBuyPrice(product.getBuyPrice());
        response.setSellPrice(product.getSellPrice());
        response.setMinStock(product.getMinStock());
        response.setStatus(product.getStatus());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());

        return response;
    }
}