package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Seller;





public interface SellerDAO extends GenericDAO<Seller, Integer> {
  
	List<Seller> findAll();
	






}


