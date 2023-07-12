package com.ecommerce.products.utils;

import com.ecommerce.products.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent baseEvent);
}
