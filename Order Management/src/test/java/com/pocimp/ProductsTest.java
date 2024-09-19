package com.pocimp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pocimp.entity.Company;
import com.pocimp.entity.Product;
import com.pocimp.entity.ProductCategory;
import com.pocimp.repository.ProductRepository;
import com.pocimp.service.ProductService;

@SpringBootTest
public class ProductsTest {
	@Autowired
	private ProductService productService;
	@MockBean
	private ProductRepository productRepository;
	@Test
	public void getProductsTest() {
	    // Mocking the behavior of productRepository.findAll
	    when(productRepository.findAll()).thenReturn(Stream.of(
	            new Product(1, "laptop", "86veg989", 87000,629898, "23-5-2024", "12-7-2022", "category for laptop", "Lavanya", "Lavanya", 
	                    new ProductCategory(), new Company(), new Date(), new Date()),
	            new Product(2, "microwave", "6098d32",30000,80729, "3-8-2025", "1-12-2023", "category for microwave", "Lavanya", "Lavanya", 
	    	                    new ProductCategory(), new Company(), new Date(), new Date()),
	            new Product(3, "chair", "587sba", 5000,7292, "23-3-2021", "1-12-2025", "category for microwave", "Lavanya", "Lavanya", 
	                    new ProductCategory(), new Company(), new Date(), new Date()),
	            new Product(4, "waterbottle", "63aw", 99,894, "1-1-2022", "12-12-2022", "category for bottle", "Lavanya", "Lavanya", 
	                    new ProductCategory(), new Company(), new Date(), new Date())
	            
	    ).collect(Collectors.toList()));

	    // Calling the getAllProducts method from the productService
	    assertEquals(4, productService.getAllProducts().size());
	}
	
	@Test
    public void getProductByIdTest() {
        int productId = 6;

        // Mocking the behavior of productRepository.findById
        Product mockProduct = new Product(productId, "waterbottle", "63aw", 99, 894, "1-1-2022", "12-12-2022", "category for bottle",
                "Lavanya", "lavanya", new ProductCategory(), new Company(), new Date(), new Date());
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        // Calling the getProductById method from the service
        Product result = productService.getProductById(productId);

        // Assert that the result is not null
        assertNotNull(result);

        // Add more assertions based on your requirements
        assertEquals(productId, result.getId());
        assertEquals("waterbottle", result.getProductName());
        // Add more assertions as needed

        // Verify that the productRepository.findById method was called once with the correct argument
        verify(productRepository, times(1)).findById(productId);
    }
	
	 
	 @Test
	    public void creatproductTest() {
	        // Creating a new ProductCategory object
		  Product product = new Product(7,"mobile", "9fe32", 150000, 300, "1-1-2022", "12-12-2022", "category for i phone",
	                "Lavanya", "lavanya", new ProductCategory(), new Company(), new Date(), new Date());

	        // Mocking the behavior of the categoryRepository.save() method
	        when(productRepository.save(product)).thenReturn(product);

	        // Calling the createProductCategory method from the service
	        Product result =productService.createProduct(product);

	        // Asserting that the returned result matches the expected productCategory
	        assertEquals(product, result);
	    }
	 
	 @Test
	    public void deleteproductTest() {
	        // Given
	        int productIdToDelete = 7;
	        Product product = new Product(7, "mobile", "9fe32", 150000, 300, "1-1-2022", "12-12-2022", "category for i phone",
	                "Lavanya", "lavanya", new ProductCategory(), new Company(), new Date(), new Date());

	        // Mocking the behavior of categoryRepository.findById
	        when(productRepository.findById(productIdToDelete)).thenReturn(Optional.of(product));

	        // When
	        productService.deleteProduct(productIdToDelete);

	        // Then
	        // Verify that the delete method of categoryRepository is called with the correct argument
	        verify(productRepository, times(1)).delete(product);
	    }
	 
	 
	 @Test
	    public void updateProductTest() {
	        // Mock existing product
	        int productId = 1;
	        Product existingProduct = new Product(productId, "Laptop", "86veg989", 87000,629898, "23-5-2024", "12-7-2022", "category for laptop", "Lavanya", "Lavanya", 
                    new ProductCategory(), new Company(), new Date(), new Date());

	        // Mock updated product
	        Product updatedProduct = new Product(productId, "apple", "XYZ789", 150, 1500,
	                "2024-12-31", "2022-02-01", "Updated apple description", "Lavanya", "Lavanya",
	                new ProductCategory(), new Company(), new Date(), new Date());

	        // Mock repository behavior
	        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
	        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

	        // Call the updateProduct method from the service
	        Product result = productService.updateProduct(productId, updatedProduct);

	        // Assert that the result is not null
	        assertNotNull(result);

	        // Add more assertions based on your requirements
	        assertEquals(productId, result.getId());
	        assertEquals("apple", result.getProductName());
	        assertEquals("XYZ789", result.getProductCode());
	        assertEquals(150, result.getTotalStock());
	        assertEquals(1500, result.getCost());
	        // ... (add more assertions for other properties)

	        // Verify that the repository methods were called
	        verify(productRepository, times(1)).findById(productId);
	        verify(productRepository, times(1)).save(existingProduct);
	    }
	
}





