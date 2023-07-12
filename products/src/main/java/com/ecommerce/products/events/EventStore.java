package com.ecommerce.products.events;

import java.util.List;

public interface EventStore {
    void saveEvent(long aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(long aggregateId);
    List<Long> getAggregateIds();
}
