package com.ecommerce.products.exceptions;

public class AggregateNotFoundException extends RuntimeException{
    public AggregateNotFoundException(String message){
        super(message);
    }
}
