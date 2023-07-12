package com.ecommerce.products.repositories;

import com.ecommerce.products.models.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products, Long> {
}
