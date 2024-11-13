package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Product;





public interface ProductDAO extends GenericDAO<Product, Integer> {
  
	List<Product> findAll();
	






}


