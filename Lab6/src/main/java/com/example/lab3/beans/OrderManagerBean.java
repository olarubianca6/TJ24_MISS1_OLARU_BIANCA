package com.example.lab3.beans;

import com.example.lab3.entities.Client;
import com.example.lab3.entities.Order;
import com.example.lab3.entities.Product;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@Stateful
@RolesAllowed({"Customer"})
public class OrderManagerBean {

    private Map<Product, Integer> orderItems = new HashMap<>();

    @PersistenceContext
    private EntityManager em;

    @EJB
    private StockCheckBean stockChecker;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addProductToOrder(Long productId, int quantity) {
        Product product = em.find(Product.class, productId);
        if (product == null || stockChecker.checkStock(productId) < quantity) {
            throw new IllegalArgumentException("Insufficient stock for " + productId);
        }
        orderItems.put(product, quantity);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void confirmOrder(Long clientId) {
        Client client = em.find(Client.class, clientId);
        if (client == null) throw new IllegalArgumentException("Invalid client ID");

        Order order = new Order();
        order.setClient(client);
        order.setProducts(new HashMap<>(orderItems));

        for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
            entry.getKey().decrementStock(entry.getValue());
            em.merge(entry.getKey());
        }

        em.persist(order);
        orderItems.clear();
    }
}
