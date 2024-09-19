package com.pocimp.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pocimp.entity.Order;
import com.pocimp.repository.OrderRepository;

@Service
public class OrderService {
	
@Autowired
   private OrderRepository orderRepository;
   
	 public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }
	 

    public Order getOrderById(int id) {
	        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

   public Order createOrder(Order order) {
        
        return orderRepository.save(order);
   }
    
    public void deleteOrder(int id) {
       Order order = getOrderById(id);
       orderRepository.delete(order);
   }
   
    
    public Order updateOrder(int id, Order updatedOrder) {
        Order existingOrder = getOrderById(id);

        // Update the fields of the existing order with the values from the updated order
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setTotalUnit(updatedOrder.getTotalUnit());
        existingOrder.setTotalCost(updatedOrder.getTotalCost());
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        existingOrder.setCreatedby(updatedOrder.getCreatedby());
        existingOrder.setModifiedby(updatedOrder.getModifiedby());
        existingOrder.setProduct(updatedOrder.getProduct());
        existingOrder.setCustomer(updatedOrder.getCustomer());
        // Update other fields as needed

        // Save the updated order to the repository
        return orderRepository.save(existingOrder);
    }
    }
	
	
	
	
