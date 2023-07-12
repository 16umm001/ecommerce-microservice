package com.ecommerce.products.events;

import com.ecommerce.products.aggregates.ProductAggregate;
import com.ecommerce.products.exceptions.AggregateNotFoundException;
import com.ecommerce.products.exceptions.ConcurrencyException;
import com.ecommerce.products.models.EventModel;
import com.ecommerce.products.repositories.EventStoreRepository;
import com.ecommerce.products.utils.GenerateUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductEventStore implements EventStore{
    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Autowired
    private GenerateUID generateUID;

    @Override
    public void saveEvent(long aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if(expectedVersion != -1 && eventStream.get(eventStream.size()-1).getVersion() != expectedVersion){
            throw new ConcurrencyException();
        }
        int version = expectedVersion;

        for(var event: events){
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .id(generateUID.getId())
                    .timestamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(ProductAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .baseEvent(event)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            System.out.println("persistent event: " + persistedEvent);
            // implement kafka producer event
        }
    }

    @Override
    public List<BaseEvent> getEvents(long aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if(eventStream == null || eventStream.isEmpty()){
            throw new AggregateNotFoundException("Incorrect Account id provided!");
        }
        return eventStream.stream().map(x -> x.getBaseEvent()).collect(Collectors.toList());
    }

    @Override
    public List<Long> getAggregateIds() {
        var eventStream = eventStoreRepository.findAll();
        if(eventStream == null || eventStream.isEmpty()){
            throw new IllegalStateException("Could not retrieve event stream from event store!");
        }
        return eventStream.stream().map(EventModel::getAggregateIdentifier).distinct().collect(Collectors.toList());
    }
}
