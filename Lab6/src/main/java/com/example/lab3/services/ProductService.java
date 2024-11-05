package com.example.lab3.services;

import com.example.lab3.entities.Product;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import javax.sql.DataSource;

@RequestScoped
public class ProductService {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped
    private EntityManager em;

    @Resource(name = "jdbc/products")
    private DataSource dataSource;

    public ProductService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Lab3PU");
    }

    @Transactional
    public void addProduct(Product product) {
        try {
            em.persist(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product findProductById(Long id) {
        try {
            return em.find(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void updateProduct(Product product) {
        try {
            em.merge(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}