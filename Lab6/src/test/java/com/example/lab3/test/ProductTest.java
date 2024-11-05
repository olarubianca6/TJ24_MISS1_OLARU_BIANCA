package com.example.lab3.test;

import com.example.lab3.entities.Product;
import com.example.lab3.services.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Lab3PU");
        entityManager = entityManagerFactory.createEntityManager();
        productService = new ProductService();
    }

    @Test
    public void productTest() {
        entityManager.getTransaction().begin();

        Product newProduct = new Product();
        newProduct.setId(122L);
        newProduct.setName("Test product");
        newProduct.setDescription("Test description");
        newProduct.setPrice(50);

        productService.addProduct(newProduct);
        entityManager.getTransaction().commit();

        Product foundProduct = productService.findProductById(newProduct.getId());

        assertNotNull(foundProduct);
        assertEquals("Test product", foundProduct.getName());
        assertEquals(50.0, foundProduct.getPrice());
    }

    @AfterEach
    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}