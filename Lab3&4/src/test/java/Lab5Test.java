import com.example.lab3.Product;
import com.example.lab3.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Lab5Test {

    @Test
    public void productTest() {
        ProductService productService = new ProductService();

        Product newProduct = new Product(122L, "Test product", "Test description", 50);
        productService.addProduct(newProduct);

        Product foundProduct = productService.findProductById(newProduct.getId());

        Assertions.assertEquals("Test product", foundProduct.getName());
        Assertions.assertEquals(50, foundProduct.getPrice());
    }
}
