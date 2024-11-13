package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Buyer;





public interface BuyerDAO extends GenericDAO<Buyer, Integer> {
  
	List<Buyer> findAll();
	






}


