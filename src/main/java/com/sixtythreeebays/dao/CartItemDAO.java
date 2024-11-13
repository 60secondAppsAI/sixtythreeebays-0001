package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.CartItem;





public interface CartItemDAO extends GenericDAO<CartItem, Integer> {
  
	List<CartItem> findAll();
	






}


