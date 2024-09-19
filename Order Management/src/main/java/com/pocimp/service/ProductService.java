package com.pocimp.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocimp.entity.Product;
import com.pocimp.repository.ProductRepository;


@Service
public class ProductService {
	@Autowired 
    private  ProductRepository productRepository;
     
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        // Add any validation logic if needed before saving the product
        return productRepository.save(product);
    }
    public Product updateProduct(int id, Product product) {
        Product existingProduct = getProductById(id);

        // Update the existing product properties here
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductCode(product.getProductCode());
        existingProduct.setTotalStock(product.getTotalStock());
        existingProduct.setCost(product.getCost());
        // ... (set other properties)

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
    
   
}