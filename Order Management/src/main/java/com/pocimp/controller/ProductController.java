package com.pocimp.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocimp.entity.Company;
import com.pocimp.entity.Product;
import com.pocimp.entity.ProductCategory;
import com.pocimp.service.CompanyService;
import com.pocimp.service.ProductCategoryService;
import com.pocimp.service.ProductService;



@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private  ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private CompanyService companyService;
    

       @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
     
        ProductCategory productCategory = productCategoryService.getProductCategoryByName(product.getCategory().getName());
        product.setCategory(productCategory);
        Company company = companyService.getCompanyByName(product.getCompany().getName());
        product.setCompany(company);
        return productService.createProduct(product);
    }
    
    @GetMapping("/getall")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("get/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product with ID " + id + " has been deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + " not found.");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        try {
            // Check if the product with the given ID exists
            productService.getProductById(id);

            // Update the product's category
            ProductCategory updatedCategory = productCategoryService
                    .getProductCategoryByName(updatedProduct.getCategory().getName());
            updatedProduct.setCategory(updatedCategory);

            // Update the product's company
            Company updatedCompany = companyService.getCompanyByName(updatedProduct.getCompany().getName());
            updatedProduct.setCompany(updatedCompany);

            // Update the product
            Product result = productService.updateProduct(id, updatedProduct);

            return ResponseEntity.ok(result);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + " not found.");
        }
    }

    
    
}


