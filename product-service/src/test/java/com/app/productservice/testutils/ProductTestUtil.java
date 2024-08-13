package com.app.productservice.testutils;

import com.app.productservice.model.dto.ProductCreateRequest;
import com.app.productservice.model.dto.ProductDto;
import com.app.productservice.model.dto.ProductResponseDto;
import com.app.productservice.model.entity.Product;
import com.app.productservice.model.enums.ProductUnit;

import java.util.List;

public class ProductTestUtil {

    public static Product createProduct(String productCode, String productName, String categoryName, String brand, ProductUnit productUnit) {
        return Product.builder()
                .productCode(productCode)
                .productName(productName)
                .categoryName(categoryName)
                .brand(brand)
                .productUnit(productUnit)
                .build();
    }

    public static ProductResponseDto createProductResponseDto(List<ProductDto> productDtoList, String message, boolean isSuccess) {
        return ProductResponseDto.builder()
                .productDtoList(productDtoList)
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    public static ProductDto createProductDto(Product product) {
        return ProductDto.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .categoryName(product.getCategoryName())
                .brand(product.getBrand())
                .productUnit(product.getProductUnit().toString())
                .build();
    }

    public static ProductCreateRequest createProductCreateRequest(Integer productCode, String productName, String categoryName, String brand, String productUnit) {
        return ProductCreateRequest.builder()
                .productCode(productCode)
                .productName(productName)
                .categoryName(categoryName)
                .brand(brand)
                .productUnit(productUnit)
                .build();
    }
}
