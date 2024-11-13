package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Bid;





public interface BidDAO extends GenericDAO<Bid, Integer> {
  
	List<Bid> findAll();
	






}


