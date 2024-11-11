package com.example.lab3.beans;

import com.example.lab3.entities.Product;

import jakarta.ejb.Stateless;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class StockCheckBean {

    @PersistenceContext
    private EntityManager em;

    @RolesAllowed({"Admin", "Customer"})
    public int checkStock(Long productId) {
        Product product = em.find(Product.class, productId);
        return (product != null) ? product.getStockQuantity() : 0;
    }
}
