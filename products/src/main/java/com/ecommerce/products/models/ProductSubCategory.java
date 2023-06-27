package com.ecommerce.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("product_subcategories")
public class ProductSubCategory {
    @Id
    private long id;
    private String name;
    private ProductSubCategoryType subCategoryType;
    private ProductCategory productCategory;
}

