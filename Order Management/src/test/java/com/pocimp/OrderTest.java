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

import com.pocimp.entity.Customer;
import com.pocimp.entity.Order;
import com.pocimp.entity.Product;
import com.pocimp.repository.OrderRepository;
import com.pocimp.service.OrderService;

@SpringBootTest
public class OrderTest {
	
	@Autowired
	private OrderService orderService;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@Test
	public void getAllOrdersTest() {
	    when(orderRepository.findAll()).thenReturn(Stream.of(
	            new Order(1, "ordered", 769, 45000, 45600, "Lavanya", "Lavanya", new Product(), new Customer(),  new Date(), new Date()),
	            new Order(2, "delivered", 698, 25000, 25000, "Lavanya", "Lavanya", new Product(), new Customer(),new Date(), new Date()),
	            new Order(3, "out for delivery", 864, 800, 800, "Lavanya", "Lavanya", new Product(), new Customer(), new Date(), new Date())
	    ).collect(Collectors.toList()));

	    assertEquals(3, orderService.getAllOrders().size());
	}

	
	@Test
	public void getOrderByIdTest() {
	    int id = 3;
	    Order mockOrder = new Order(3, "out for delivery", 864, 800, 800, "Lavanya", "Lavanya", new Product(), new Customer(), new Date(), new Date());
	    Optional<Order> optionalOrder = Optional.of(mockOrder);

	    when(orderRepository.findById(id)).thenReturn(optionalOrder);

	    // Get the result from the service
	    Order result = orderService.getOrderById(id);

	    // Assert that the result is not null
	    assertNotNull(result);

	    // Add more assertions as needed based on your requirements
	  //  assertTrue(result.isPresent());
	    assertEquals(id, result.getId());
	}
	
	 @Test
	    public void createorderTest() {
	        // Creating a new ProductCategory object
	        Order order = new Order(4, "attempted", 475,500,560, "Lavanya", "Lavanya", new Product(), new Customer(), new Date(), new Date());

	        // Mocking the behavior of the categoryRepository.save() method
	        when(orderRepository.save(order)).thenReturn(order);

	        // Calling the createProductCategory method from the service
	        Order result = orderService.createOrder(order);

	        // Asserting that the returned result matches the expected productCategory
	        assertEquals(order, result);
	    }
	 
	 @Test
	    public void deleteorderTest() {
	        // Given
	        int orderIdToDelete = 6;
	        Order order = new Order(orderIdToDelete,"out for delivery", 864,800,800,"Lavanya", "Lavanya", new Product(), new Customer(), new Date(), new Date() );

	        // Mocking the behavior of categoryRepository.findById
	        when(orderRepository.findById(orderIdToDelete)).thenReturn(Optional.of(order));

	        // When
	       orderService.deleteOrder(orderIdToDelete);

	        // Then
	        // Verify that the delete method of categoryRepository is called with the correct argument
	        verify(orderRepository, times(1)).delete(order);
	    }
	 
	 
	 @Test
	    public void updateorderTest() {
	        int orderId = 2;

	        // Mocking the behavior of categoryRepository.findById
	        Order existingOrder = new Order(orderId,"attempted", 475,500,560, "Lavanya", "Lavanya", new Product(), new Customer(), new Date(), new Date());
	        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));

	        // Mocking the behavior of categoryRepository.save
	        Order updatedOrder = new Order(orderId, "canceled", 475,500,560, "Lavanya", "Lavanya", new Product(), new Customer(), new Date(), new Date());
	        when(orderRepository.save(existingOrder)).thenReturn(updatedOrder);

	        // Call the updateProductCategory method from the service
	        Order requestOrder = new Order(orderId,"canceled", 475,500,560, "Lavanya", "Lavanya", new Product(), new Customer(), new Date(), new Date() );
	        Order result = orderService.updateOrder(orderId, requestOrder);

	        // Assert that the result is not null
	        assertNotNull(result);

	        // Add more assertions based on your requirements
	        assertEquals(orderId, result.getId());
	        assertEquals("canceled", result.getStatus());
	       // assertEquals("fruits", result.getDescription());
	       // assertEquals("lavanya", result.getModifiedby());

	        // Verify that the categoryRepository.findById and categoryRepository.save methods were called
	        verify(orderRepository, times(1)).findById(orderId);
	        verify(orderRepository, times(1)).save(existingOrder);
	    }


}
