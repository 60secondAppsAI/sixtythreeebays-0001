package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Return;





public interface ReturnDAO extends GenericDAO<Return, Integer> {
  
	List<Return> findAll();
	






}


