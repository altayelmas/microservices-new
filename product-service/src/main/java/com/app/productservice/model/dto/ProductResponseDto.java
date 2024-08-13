package com.app.productservice.model.dto;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private List<ProductDto> productDtoList;
    private String message;
    private boolean isSuccess;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseDto that = (ProductResponseDto) o;
        return isSuccess == that.isSuccess && Objects.equals(productDtoList, that.productDtoList) && Objects.equals(message, that.message);
    }
}
