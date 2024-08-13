package com.app.productservice.model.dto;

import com.app.productservice.model.enums.ProductUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {

    private String productName;
    private String brand;
    private String productUnit;

}
