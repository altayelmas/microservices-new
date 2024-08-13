package com.app.barcode.model.dto;

import lombok.*;


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

}
