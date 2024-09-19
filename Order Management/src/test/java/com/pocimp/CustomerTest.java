package com.pocimp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pocimp.entity.Customer;
import com.pocimp.repository.CustomerRepository;
import com.pocimp.service.CustomerService;

@SpringBootTest
public class CustomerTest {
	
	@Autowired
	private CustomerService customerService;
     @MockBean
	private CustomerRepository customerRepository;
     
     @Test
     void testGetAllCustomers() {
         when(customerRepository.findAll()).thenReturn(Stream.of(
                 new Customer(1, "preethi", "9788756534"),
                 new Customer(2, "deepali", "8269292571"),
                 new Customer(3, "lavanya", "9682914934"),
                 new Customer(4, "vanshika","9786243415"),
                 new Customer(5, "minnu", "7963893466")
                 
                 
                 )
                 .collect(Collectors.toList()));

         assertEquals(5, customerService.getAllCustomers().size());
     }
     
     @Test
	    public void createcustomerTest() {
	        // Creating a new ProductCategory object
	        Customer customer = new Customer(6, "hima","8898652427");

	        // Mocking the behavior of the categoryRepository.save() method
	        when(customerRepository.save(customer)).thenReturn(customer);

	        // Calling the createProductCategory method from the service
	        Customer result = customerService.createCustomer(customer);

	        // Asserting that the returned result matches the expected productCategory
	        assertEquals(customer, result);
	    }
     
     @Test
	    public void deletecustomerTest() {
	        // Given
	        int customerIdToDelete = 6;
	      Customer customer = new Customer(customerIdToDelete, "hima","8898652427");

	        // Mocking the behavior of categoryRepository.findById
	        when(customerRepository.findById(customerIdToDelete)).thenReturn(Optional.of(customer));

	        // When
	        customerService.deleteCustomer(customerIdToDelete);

	        // Then
	        // Verify that the delete method of categoryRepository is called with the correct argument
	        verify(customerRepository, times(1)).delete(customer);
	    }
     
     @Test
     public void getCustomerByIdTest() {
         int id = 2;
         Customer mockCustomer = new Customer(2, "vennela", "6872364065");

         // Create an Optional containing the mockCustomer
         Optional<Customer> optionalCustomer = Optional.of(mockCustomer);

         // Mock the behavior of findById to return the Optional<Customer>
         when(customerRepository.findById(id)).thenReturn(optionalCustomer);

         // Get the result from the service
         Customer result = customerService.getCustomerById(id);

         // Assert that the result is not null
         assertNotNull(result);

         // Add more assertions as needed based on your requirements
         assertEquals(id, result.getId());
     }
    
     @Test
	    public void updateCustomerTest() {
	        int customerId = 1;

	        // Mocking the behavior of categoryRepository.findById
	        Customer existingCustomer = new Customer(customerId,"preethi", "9788756534");
	        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

	        // Mocking the behavior of categoryRepository.save
	        Customer updatedCustomer = new Customer(customerId, "minnu", "7963893466");
	        when(customerRepository.save(existingCustomer)).thenReturn(updatedCustomer);

	        // Call the updateProductCategory method from the service
	        Customer requestCustomer = new Customer(customerId,"minnu", "7963893466" );
	       Customer result = customerService.updateCustomer(customerId, requestCustomer);

	        // Assert that the result is not null
	        assertNotNull(result);

	        // Add more assertions based on your requirements
	        assertEquals(customerId, result.getId());
	        assertEquals("minnu", result.getName());
	       // assertEquals("fruits", result.getDescription());
	       // assertEquals("lavanya", result.getModifiedby());

	        // Verify that the categoryRepository.findById and categoryRepository.save methods were called
	        verify(customerRepository, times(1)).findById(customerId);
	        verify(customerRepository, times(1)).save(existingCustomer);
	    }
    	        	
     }

