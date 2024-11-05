package com.example.lab3.test;

import com.example.lab3.repositories.ProductRepository;
import com.example.lab3.entities.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private ProductRepository productRepository;

    @BeforeAll
    public static void init() {
        emf = Persistence.createEntityManagerFactory("Lab3PU");
    }

    @BeforeEach
    public void setup() {
        em = emf.createEntityManager();
        productRepository = new ProductRepository();
        em.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        em.close();
    }

    @AfterAll
    public static void close() {
        emf.close();
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Sample Product");
        product.setPrice(100);

        productRepository.save(product);
        em.flush();

        assertNotNull(product.getId());
    }

    @Test
    public void testFindProductByName() {
        Product product = new Product();
        product.setName("Sample Product 2");
        product.setPrice(800);
        productRepository.save(product);

        Product foundProduct = productRepository.findByName("Sample Product 2");

        assertEquals("Sample Product 2", foundProduct.getName());
        assertEquals(800, foundProduct.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setName("Sample Product 3");
        product.setPrice(500);
        productRepository.save(product);

        productRepository.delete(product);

        assertNull(productRepository.find(product.getId()));
    }
}