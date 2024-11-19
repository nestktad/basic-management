package com.vn.productmanagement;

import com.vn.entities.Category;
import com.vn.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.vn.entities.Product;

@SpringBootTest
class ProductManagementApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() {
        // Create category with an existing ID (make sure ID 1 exists in your database)
        Category category = new Category();
        category.setCategoryId(1);

        // Create product
        Product product = new Product();
     //   product.setCategoryId(1);  // Set category
        product.setProductName("test2");
        product.setPrice(123);
        product.setStock(1234);
        product.setImage("img2");

        // Save the product and get the ID
        productService.saveProduct(product);
        System.out.println(product.getProductId());

        // Assert that the product ID is generated (i.e., it should not be null)
        Assertions.assertNotNull(product.getProductId());
    }
}
