package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Shipping;





public interface ShippingDAO extends GenericDAO<Shipping, Integer> {
  
	List<Shipping> findAll();
	






}


