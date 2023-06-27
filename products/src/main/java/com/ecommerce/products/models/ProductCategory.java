package com.ecommerce.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("product_categories")
public class ProductCategory {
    @Id
    private long id;
    private String name;

    private ProductCategoryType categoryType;
    private Set<ProductSubCategory> subCategorySet;

    public ProductCategory(long id, String name, ProductCategoryType categoryType) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
    }
}
