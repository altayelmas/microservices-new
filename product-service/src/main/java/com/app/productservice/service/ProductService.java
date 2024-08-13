package com.app.productservice.service;

import com.app.productservice.model.dto.ProductCreateRequest;
import com.app.productservice.model.dto.ProductDto;
import com.app.productservice.model.dto.ProductResponseDto;
import com.app.productservice.model.dto.ProductUpdateRequest;

public interface ProductService {
    ProductResponseDto createProduct(ProductCreateRequest productCreateRequest);

    ProductResponseDto getProduct(String id);

    ProductResponseDto deleteProduct(String id);

    ProductResponseDto updateProduct(String id, ProductUpdateRequest productUpdateRequest);
}
