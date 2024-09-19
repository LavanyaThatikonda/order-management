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

import com.pocimp.entity.ProductCategory;
import com.pocimp.repository.ProductCategoryRepository;
import com.pocimp.service.ProductCategoryService;


@SpringBootTest
class ProductCategoryTests {

	@Autowired
	private  ProductCategoryService productCategoryService;
	@MockBean
	private ProductCategoryRepository categoryRepository;
	
	
 
	@Test
    public void GetAllCategory() {
        when(categoryRepository.findAll()).thenReturn(Stream.of(
                new ProductCategory(1, "electronics", "category for electronics", "Lavanya", "Lavanya", new Date(), new Date()),
                new ProductCategory(2, "kitchen items", "category for kitchen items", "Lavanya", "Lavanya", new Date(), new Date()),
                new ProductCategory(3, "furniture", "category for furniture", "Lavanya", "Lavanya", new Date(), new Date()),
                new ProductCategory(4, "plastics", "category for plastics", "Lavanya", "Lavanya", new Date(), new Date()),
                new ProductCategory(5, "clothes", "category for clothes", "Lavanya", "Lavanya", new Date(), new Date())
                
        ).collect(Collectors.toList()));

        assertEquals(5, productCategoryService.getAllCategories().size());
    }
	 @Test
	    public void getcategorybynameTest() {
	        String name = "electronics";
	        ProductCategory mockCategory = new ProductCategory(1, name, "category for electronics", "Lavanya", "Lavanya", new Date(), new Date());
	        when(categoryRepository.findByName(name)).thenReturn(mockCategory);

	        // Get the result from the service
	        ProductCategory result = productCategoryService.getProductCategoryByName(name);

	        // Assert that the result is not null
	        assertNotNull(result);

	        // Add more assertions as needed based on your requirements
	        assertEquals(name, result.getName());
	    }
	 @Test
	    public void createcategoryTest() {
	        // Creating a new ProductCategory object
	        ProductCategory productCategory = new ProductCategory(6, "bags", "category for bags", "Lavanya", "Lavanya", new Date(), new Date());

	        // Mocking the behavior of the categoryRepository.save() method
	        when(categoryRepository.save(productCategory)).thenReturn(productCategory);

	        // Calling the createProductCategory method from the service
	        ProductCategory result = productCategoryService.createProductCategory(productCategory);

	        // Asserting that the returned result matches the expected productCategory
	        assertEquals(productCategory, result);
	    }
	 
	 @Test
	    public void deletecategoryTest() {
	        // Given
	        int categoryIdToDelete = 6;
	        ProductCategory category = new ProductCategory(categoryIdToDelete, "bags", "category for bags", "Lavanya", "Lavanya", new Date(), new Date());

	        // Mocking the behavior of categoryRepository.findById
	        when(categoryRepository.findById(categoryIdToDelete)).thenReturn(Optional.of(category));

	        // When
	        productCategoryService.deleteProductCategory(categoryIdToDelete);

	        // Then
	        // Verify that the delete method of categoryRepository is called with the correct argument
	        verify(categoryRepository, times(1)).delete(category);
	    }
	 @Test
	    public void updateProductCategoryTest() {
	        int categoryId = 1;

	        // Mocking the behavior of categoryRepository.findById
	        ProductCategory existingCategory = new ProductCategory(categoryId, "Electronics", "Category for electronics",
	                "Lavanya", "Lavanya", new Date(), new Date());
	        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));

	        // Mocking the behavior of categoryRepository.save
	        ProductCategory updatedCategory = new ProductCategory(categoryId, "fruits", " category for fruits",
	                "Lavanya", "Lavanya", new Date(), new Date());
	        when(categoryRepository.save(existingCategory)).thenReturn(updatedCategory);

	        // Call the updateProductCategory method from the service
	        ProductCategory requestCategory = new ProductCategory(categoryId, "fruits", " category for fruits",
	                "Lavanya", "Lavanya", new Date(), new Date());
	        ProductCategory result = productCategoryService.updateProductCategory(categoryId, requestCategory);

	        // Assert that the result is not null
	        assertNotNull(result);

	        // Add more assertions based on your requirements
	        assertEquals(categoryId, result.getId());
	        assertEquals("fruits", result.getName());
	       // assertEquals("fruits", result.getDescription());
	       // assertEquals("lavanya", result.getModifiedby());

	        // Verify that the categoryRepository.findById and categoryRepository.save methods were called
	        verify(categoryRepository, times(1)).findById(categoryId);
	        verify(categoryRepository, times(1)).save(existingCategory);
	    }
	}

	 