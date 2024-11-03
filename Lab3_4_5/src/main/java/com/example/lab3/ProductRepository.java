package com.example.lab3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public Product find(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    public Product findByName(String name) {
        return em.createNamedQuery("Product.findByName", Product.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Transactional
    public void save(Product product) {
        em.persist(product);
    }

    @Transactional
    public void update(Product product) {
        em.merge(product);
    }

    @Transactional
    public void delete(Product product) {
        em.remove(em.contains(product) ? product : em.merge(product));
    }
}
