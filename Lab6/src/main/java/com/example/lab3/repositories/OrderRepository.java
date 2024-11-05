package com.example.lab3.repositories;

import com.example.lab3.Logged;
import com.example.lab3.entities.Client;
import com.example.lab3.entities.Product;
import com.example.lab3.entities.Order;

import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Map;

@Stateful
@Logged
public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean createOrder(Client client, Map<Product, Integer> products) {
        Order order = new Order();
        order.setClient(client);
        order.setProducts(products);
        entityManager.persist(order);
        return true;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeOrder(Long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        if (order != null) {
            entityManager.remove(order);
        }
    }}
