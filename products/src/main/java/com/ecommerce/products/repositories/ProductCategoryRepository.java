package com.ecommerce.products.repositories;

import com.ecommerce.products.models.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductCategoryRepository extends MongoRepository<ProductCategory, Long> {
}
