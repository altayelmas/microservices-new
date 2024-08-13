package com.app.productservice.model.mapper;

import com.app.productservice.model.dto.ProductDto;
import com.app.productservice.model.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class ProductMapper {

    public ProductDto toProductDto(Product product) {

        return ProductDto.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .categoryName(product.getCategoryName())
                .brand(product.getBrand())
                .productUnit(product.getProductUnit().toString())
                .build();
    }

    public List<ProductDto> toProductDtoList(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            productDtoList.add(toProductDto(product));
        }
        return productDtoList;
    }
}
