package com.project.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.order.entity.Order;

public interface ReorderRepository extends JpaRepository<Order, String>{
	Order findByOrderid(String orderid);
}
