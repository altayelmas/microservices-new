package com.app.categoryservice.model.entity;

import com.app.categoryservice.model.enums.CategoryCode;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryCode categoryCode;
}
