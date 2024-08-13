package com.app.categoryservice.service.strategy.impl;

import com.app.categoryservice.model.entity.Category;
import com.app.categoryservice.model.enums.CategoryCode;
import com.app.categoryservice.service.strategy.CategoryStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.app.categoryservice.service.impl.CategoryServiceImpl.addCategory;

@Service
public class EtCategory implements CategoryStrategy {

    @PostConstruct
    @Override
    public void register() {
        Category category = Category.builder()
                .name("ET")
                .categoryCode(CategoryCode.ET)
                .build();

        addCategory(category.getName(), this);
    }

    @Override
    public Category getCategory() {
        return Category.builder()
                .name("ET")
                .categoryCode(CategoryCode.ET)
                .build();
    }


}
