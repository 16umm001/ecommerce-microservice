package com.ecommerce.products.models;

import com.ecommerce.products.events.BaseEvent;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
    @Id
    private long id;
    private Date timestamp;
    private long aggregateIdentifier;
    private String aggregateType;
    private int version;
    private String eventType;
    private BaseEvent baseEvent;
}
