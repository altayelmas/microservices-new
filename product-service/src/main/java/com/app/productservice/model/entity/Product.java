package com.app.productservice.model.entity;

import com.app.productservice.model.enums.ProductUnit;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"productName"})})
// @Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"productName"}), @UniqueConstraint(columnNames = {"categoryName"})})
public class Product {

    @Id
    @Column(length = 5)
    private String productCode;
    private String productName;
    @NotNull
    private String categoryName;
    @NotNull
    private String brand;
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductUnit productUnit;

}
