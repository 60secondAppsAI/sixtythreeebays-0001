package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Discount;





public interface DiscountDAO extends GenericDAO<Discount, Integer> {
  
	List<Discount> findAll();
	






}


