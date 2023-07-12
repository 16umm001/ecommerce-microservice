package com.ecommerce.products.repositories;

import com.ecommerce.products.models.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventStoreRepository extends MongoRepository<EventModel, Long> {
    List<EventModel> findByAggregateIdentifier(long aggregateIdentifier);
}
