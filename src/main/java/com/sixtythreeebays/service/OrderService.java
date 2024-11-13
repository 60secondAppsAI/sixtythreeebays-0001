package com.sixtythreeebays.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sixtythreeebays.domain.Order;
import com.sixtythreeebays.dto.OrderDTO;
import com.sixtythreeebays.dto.OrderSearchDTO;
import com.sixtythreeebays.dto.OrderPageDTO;
import com.sixtythreeebays.dto.OrderConvertCriteriaDTO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface OrderService extends GenericService<Order, Integer> {

	List<Order> findAll();

	ResultDTO addOrder(OrderDTO orderDTO, RequestDTO requestDTO);

	ResultDTO updateOrder(OrderDTO orderDTO, RequestDTO requestDTO);

    Page<Order> getAllOrders(Pageable pageable);

    Page<Order> getAllOrders(Specification<Order> spec, Pageable pageable);

	ResponseEntity<OrderPageDTO> getOrders(OrderSearchDTO orderSearchDTO);
	
	List<OrderDTO> convertOrdersToOrderDTOs(List<Order> orders, OrderConvertCriteriaDTO convertCriteria);

	OrderDTO getOrderDTOById(Integer orderId);







}





