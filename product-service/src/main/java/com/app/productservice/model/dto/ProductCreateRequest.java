package com.app.productservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {

    private Integer productCode;
    private String productName;
    private String categoryName;
    private String brand;
    private String productUnit;

}
