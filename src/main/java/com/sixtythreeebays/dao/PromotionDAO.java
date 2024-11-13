package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Promotion;





public interface PromotionDAO extends GenericDAO<Promotion, Integer> {
  
	List<Promotion> findAll();
	






}


