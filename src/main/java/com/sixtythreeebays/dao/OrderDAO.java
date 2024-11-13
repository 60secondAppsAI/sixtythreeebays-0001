package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Order;





public interface OrderDAO extends GenericDAO<Order, Integer> {
  
	List<Order> findAll();
	






}


