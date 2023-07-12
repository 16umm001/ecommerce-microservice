package com.ecommerce.products.utils;

import com.ecommerce.products.commands.BaseCommand;

@FunctionalInterface
public interface CommandHandlerMethod <T extends BaseCommand> {
    void handle(T command);
}
