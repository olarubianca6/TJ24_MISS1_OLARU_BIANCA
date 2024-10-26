package com.example.lab3;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@RequestScoped
public class ProductService {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    private EntityManager em;

    @Resource(name = "jdbc/products")
    private javax.sql.DataSource dataSource;

    public ProductService() {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/products");
            entityManagerFactory = Persistence.createEntityManagerFactory("Lab3PU");
            em = entityManagerFactory.createEntityManager();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        if (em == null || !em.isOpen()) {
            em = entityManagerFactory.createEntityManager();
        }
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
        Product product = null;
        try {
            product = em.find(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Transactional
    public void updateProduct(Product product) {
        init();
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
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}