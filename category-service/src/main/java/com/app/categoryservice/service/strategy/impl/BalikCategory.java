package com.app.categoryservice.service.strategy.impl;

import com.app.categoryservice.model.entity.Category;
import com.app.categoryservice.model.enums.CategoryCode;
import com.app.categoryservice.service.strategy.CategoryStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.app.categoryservice.service.impl.CategoryServiceImpl.addCategory;

@Service
public class BalikCategory implements CategoryStrategy {

    @PostConstruct
    @Override
    public void register() {
        Category category = Category.builder()
                .name("BALIK")
                .categoryCode(CategoryCode.BL)
                .build();

        addCategory(category.getName(), this);
    }

    @Override
    public Category getCategory() {
        return Category.builder()
                .name("BALIK")
                .categoryCode(CategoryCode.BL)
                .build();
    }
}
