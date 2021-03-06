package com.project.shop.order.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.shop.exception.Project23Exception;
import com.project.shop.order.dto.OrderDTO;
import com.project.shop.order.entity.Order;
import com.project.shop.order.repository.OrderRepository;
import com.project.shop.order.repository.ReorderRepository;


@Service
@Transactional
public class OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderRepository orderrepo;
	@Autowired
	ReorderRepository reorderRepo;
	
	//get specific order by id
	public List<OrderDTO> getSpecificOrder(String orderid) throws Project23Exception{
		logger.info("Order details of Id {}", orderid);
		
		Iterable<Order> order = orderrepo.findByOrderid(orderid);
		List<OrderDTO> orderDTO = new ArrayList<OrderDTO>();
		order.forEach(ord -> {
		
			orderDTO.add(OrderDTO.valueOf(ord));
		});
		
		if(orderDTO.isEmpty())
			throw new Project23Exception("Service.ORDERD_NOT_FOUND");
		logger.info("{}", orderDTO);
		return orderDTO;
	}
	
	//Get all order details
	
	public List<OrderDTO> getAllOrders() throws Project23Exception {

		Iterable<Order> orders = orderrepo.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();

		orders.forEach(order -> {
			
			OrderDTO orderDTO = OrderDTO.valueOf(order);
			orderDTOs.add(orderDTO);
		});
        if(orderDTOs.isEmpty())
        	throw new Project23Exception ("Service.ORDERS_NOT_FOUND");
		logger.info("Order Details : {}", orderDTOs);
		return orderDTOs;
	}
	
	//Place order
	public String saveOrder(OrderDTO orderDTO) throws Project23Exception {
		Order order = orderrepo.getOrderByBuyeridAndAddress(orderDTO.getBuyerid(),orderDTO.getAddress());
		if(order!=null) {
			return order.getOrderid();
	}
		else {
		throw new Project23Exception("Service.ORDER_NOT_PLACED");
	  }
	}
	
	//Reorder
	public boolean reOrder(OrderDTO orderDTO) throws Project23Exception{
		logger.info("Reordering the order {}", orderDTO.getOrderid());
		Order ord = reorderRepo.findByOrderid(orderDTO.getOrderid());
		if (ord != null && ord.getOrderid().equals(orderDTO.getOrderid())) {
			return true;
		}
		else {
		  throw new Project23Exception("Service.ORDER_NOT_PLACED");
	   }
	}
    //Delete order
	public void deleteOrder(String orderid) throws Project23Exception {
    Optional<Order> ord = orderrepo.findById(orderid);
    ord.orElseThrow(() -> new Project23Exception("Service.ORDERS_NOT_FOUND"));
    orderrepo.deleteById(orderid);
	}
}