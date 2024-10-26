package com.example.lab3;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Persistence;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;

import static java.lang.System.getProperties;

@RequestScoped
public class ProductService {

    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;

    @Resource(name= "jdbc/products")
    private DataSource dataSource;

    public ProductService() {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/products");

            entityManagerFactory = (EntityManagerFactory) Persistence.createEntityManagerFactory("Lab3PU", getProperties());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
