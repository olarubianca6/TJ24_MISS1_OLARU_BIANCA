package com.example.lab3.beans;

import com.example.lab3.entities.Order;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.DependsOn;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Startup
@DependsOn("OrderManagerBean")
public class OrderMapBean {
    private final Map<Long, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void removeOrder(Long orderId) {
        orders.remove(orderId);
    }

}
