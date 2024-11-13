package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Review;





public interface ReviewDAO extends GenericDAO<Review, Integer> {
  
	List<Review> findAll();
	






}


