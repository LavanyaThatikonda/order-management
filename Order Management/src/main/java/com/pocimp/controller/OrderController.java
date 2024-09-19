package com.pocimp.controller;

import java.util.List;


import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pocimp.entity.Customer;
import com.pocimp.entity.Order;
import com.pocimp.entity.Product;
import com.pocimp.service.CustomerService;
import com.pocimp.service.OrderService;
import com.pocimp.service.ProductService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/create")
	public Order createOrder(@RequestBody Order order) {
        Product product = productService.getProductById(order.getProduct().getId());
        order.setProduct(product);
        Customer customer = customerService.getCustomerById(order.getCustomer().getId());
        order.setCustomer(customer);
        return orderService.createOrder(order);
    }
	
        
	     
	
	  @GetMapping("/orders/{id}")
	  public Order getOrderById(@PathVariable int orderId) {
	      return orderService.getOrderById(orderId);
	  }
	  @GetMapping("/getall")
	  public List<Order> getAllOrders() {
	      return orderService.getAllOrders();
	  }
	  


      @DeleteMapping("/delete/{orderId}")
       public void deleteOrder(@PathVariable int orderId) {
    orderService.deleteOrder(orderId);
     }
      
      @PutMapping("/{id}")
      public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order updatedOrder) {
          try {
              Order updatedOrderResult = orderService.updateOrder(id, updatedOrder);
              return ResponseEntity.ok(updatedOrderResult);
          } catch (EntityNotFoundException e) {
              return ResponseEntity.notFound().build();
          }
      
     
   }
}




















