package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Refund;





public interface RefundDAO extends GenericDAO<Refund, Integer> {
  
	List<Refund> findAll();
	






}


