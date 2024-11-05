package com.example.lab3.beans;

import com.example.lab3.DatabaseConnection;
import com.example.lab3.entities.Product;
import com.example.lab3.services.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ProductBean {
    private List<Product> products;
    private Product selectedProduct;
    private ProductService productService = new ProductService();

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        String sql = "SELECT * FROM products";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String editProduct(Product product) {
        this.selectedProduct = product;
        return "dataEdit.xhtml";
    }

    public String saveProduct() {
        if (selectedProduct != null) {
            productService.updateProduct(selectedProduct);
        }
        return "dataView.xhtml";
    }
}