package com.app.barcode.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private List<ProductDto> productDtoList;
    private String message;
    private boolean isSuccess;

}
