package com.app.productservice.service.strategy.impl;

import com.app.productservice.model.enums.ProductUnit;
import com.app.productservice.service.strategy.ProductUnitStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.app.productservice.service.impl.ProductServiceImpl.addProductUnitStrategy;

@Service
public class Piece implements ProductUnitStrategy {

    @PostConstruct
    @Override
    public void register() {
        addProductUnitStrategy("PIECE", this);
    }

    @Override
    public ProductUnit getProductUnit() {
        return ProductUnit.PIECE;
    }

}
