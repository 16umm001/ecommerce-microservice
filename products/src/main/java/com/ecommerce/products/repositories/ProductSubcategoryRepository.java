package com.ecommerce.products.repositories;

import com.ecommerce.products.models.ProductSubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductSubcategoryRepository extends MongoRepository<ProductSubCategory, Long> {

}
