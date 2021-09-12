package com.project.shop.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.order.entity.ProductsOrder;

public interface  ProductsOrderRepo extends JpaRepository<ProductsOrder, String>{
	
	List<ProductsOrder> findByProdid(String prodid);
	}

