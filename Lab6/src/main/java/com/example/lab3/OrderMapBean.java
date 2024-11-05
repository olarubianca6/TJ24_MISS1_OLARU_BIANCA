package com.example.lab3;

import com.example.lab3.entities.Order;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Startup
public class OrderMapBean {
    private final Map<Long, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void removeOrder(Long orderId) {
        orders.remove(orderId);
    }

    public List<Order> getOrdersByClientId(Long clientId) {
        return orders.values().stream()
                .filter(order -> order.getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }
}
