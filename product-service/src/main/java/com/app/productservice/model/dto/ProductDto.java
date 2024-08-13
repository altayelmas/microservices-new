package com.app.productservice.model.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productCode;
    private String productName;
    private String categoryName;
    private String brand;
    private String productUnit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(productCode, that.productCode) && Objects.equals(productName, that.productName) && Objects.equals(categoryName, that.categoryName) && Objects.equals(brand, that.brand) && Objects.equals(productUnit, that.productUnit);
    }
}
