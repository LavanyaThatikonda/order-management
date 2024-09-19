package com.pocimp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocimp.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

	//Order createOrder(Order order);

}
