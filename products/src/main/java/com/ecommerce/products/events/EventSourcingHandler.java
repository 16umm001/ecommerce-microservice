package com.ecommerce.products.events;

import com.ecommerce.products.aggregates.AggregateRoot;
import com.ecommerce.products.aggregates.ProductAggregate;
import com.ecommerce.products.utils.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;


@Service
public class EventSourcingHandler {
    @Autowired
    private EventStore eventStore;

    @Autowired
    private EventProducer eventProducer;

    public void save(AggregateRoot aggregateRoot) {
        eventStore.saveEvent(aggregateRoot.getId(),
                aggregateRoot.getUncommittedChanges(),
                aggregateRoot.getVersion());
        aggregateRoot.markChangesAsCommitted();
    }

    public ProductAggregate getById(long id) {
        var aggregate = new ProductAggregate();
        var events = eventStore.getEvents(id);
        if(events != null && !events.isEmpty())
        {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

    public void republishEvents() {
        var aggregateIds = eventStore.getAggregateIds();
        for(var aggregateId: aggregateIds){
            var aggregate = getById(aggregateId);
            if(aggregate==null){
                continue;
            }
            var events = eventStore.getEvents(aggregateId);
            for(var event: events){
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }

    }
}
