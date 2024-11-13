package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Cart;





public interface CartDAO extends GenericDAO<Cart, Integer> {
  
	List<Cart> findAll();
	






}


