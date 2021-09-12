package com.project.shop.order.controller;

import java.util.List;
import java.lang.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.shop.exception.Project23Exception;
import com.project.shop.order.dto.OrderDTO;
import com.project.shop.order.dto.ProductsOrderedDTO;
import com.project.shop.order.service.OrderService;
import com.project.shop.order.service.ProductsOrderService;

@RestController
@CrossOrigin
@RequestMapping

public class OrderController {

	      Logger logger = LoggerFactory.getLogger(this.getClass());
          @Autowired
          Environment environment;
          @Autowired
          private OrderService orderservice;
          @Autowired
          ProductsOrderService productService;


// Get orders by ID
@RequestMapping(value = "/api/orders/{orderid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<OrderDTO>> getSpecificOrder(@PathVariable String orderid) throws Project23Exception {
	logger.info("order details {}", orderid);
        List<OrderDTO> orders =orderservice.getSpecificOrder(orderid);
	return new ResponseEntity<>(orders, HttpStatus.OK);
}

//Get all orders
	@GetMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDTO>> getAllOrder() throws Project23Exception {
		logger.info("Fetching all orders");
            List<OrderDTO> orderdto = orderservice.getAllOrders();
		return new ResponseEntity<>(orderdto, HttpStatus.OK);
	}
//Placing an order
	@PostMapping(value = "/api/orders/placeorders", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> searchOrder(@RequestBody OrderDTO orderDTO) throws Project23Exception {
		String orderid = orderservice.saveOrder(orderDTO);
                String successMessage = environment.getProperty("API.ORDER_SUCCESS") + orderid;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
//Reorder
        @PostMapping(value = "/api/orders/reorder/{orderid}/{orderid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	    ResponseEntity<String> reOrder(@RequestBody OrderDTO orderDTO) throws Project23Exception { 
           logger.info("Re ordering the order{}", orderDTO.getOrderid());
           boolean order = orderservice.reOrder(orderDTO);
           String successMessage = environment.getProperty("API.ORDER_SUCCESS") + order;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
//Get products ordered by product id
	@GetMapping(value = "/api/productsordered/{prodid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductsOrderedDTO>> getProductById(@PathVariable String prodid) throws Project23Exception {
	logger.info("Product details request for ordered product {} ", prodid);
            List<ProductsOrderedDTO> orders = productService.getProductById(prodid);
		return new ResponseEntity<>(orders, HttpStatus.CREATED);
	}
//Delete order
	@DeleteMapping(value = "/order/{orderid}")
        public ResponseEntity<String> deleteOrder(@PathVariable String orderid) throws Project23Exception{
        orderservice.deleteOrder(orderid);
        String successMessage = environment.getProperty("API.DELETE_SUCCESS");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
     }
}