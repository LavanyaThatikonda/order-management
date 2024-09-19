package com.pocimp.service;

import java.util.List;


import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocimp.entity.Customer;
import com.pocimp.repository.CustomerRepository;



@Service
public class CustomerService {
	
	    @Autowired
	    private CustomerRepository customerRepository;

	    public Customer createCustomer(Customer customer) {
	        Customer existingCustomer = customerRepository.findByName(customer.getName());
	        if (existingCustomer != null) {
	            throw new RuntimeException("Customer already exists");
	        }
	        return customerRepository.save(customer);
	    }

	    public List<Customer> getAllCustomers() {
	        return customerRepository.findAll();
	    }

	    public Customer getCustomerById(int id) {
	        return customerRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
	    }

	    public void deleteCustomer(int id) {
	        Customer customer = customerRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

	        customerRepository.delete(customer);
	    }

	    public Customer updateCustomer(int id, Customer customer) {
	        Customer existingCustomer = customerRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

	        existingCustomer.setName(customer.getName());
	        existingCustomer.setMobilenumber(customer.getMobilenumber());

	        return customerRepository.save(existingCustomer);
	    }
	}


