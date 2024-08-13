package com.app.productservice.service.strategy;

import com.app.productservice.model.enums.ProductUnit;

public interface ProductUnitStrategy {
    void register();
    ProductUnit getProductUnit();
}
