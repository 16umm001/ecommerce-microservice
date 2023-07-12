package com.ecommerce.products.commands;

import com.ecommerce.products.utils.CommandHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ProductCommandDispatcher {
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap();

    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    public void send(BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if(handlers==null || handlers.size() == 0){
            throw new RuntimeException("No command handler was registered!");
        }
        if(handlers.size() > 1){
            throw new RuntimeException("Cannot sent command to more than one handler!");
        }
        handlers.get(0).handle(command);
    }
}
