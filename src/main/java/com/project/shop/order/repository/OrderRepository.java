package com.project.shop.order.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.shop.order.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	List<Order> findByOrderid(String orderid);
//@Query("From Order as order"
//		+ " WHERE buyerid = :buyerid and address + :address")
public Order getOrderByBuyeridAndAddress(@Param("buyerid") String buyerid,@Param("address")String address);


	
}