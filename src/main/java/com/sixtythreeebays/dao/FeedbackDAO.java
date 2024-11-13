package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Feedback;





public interface FeedbackDAO extends GenericDAO<Feedback, Integer> {
  
	List<Feedback> findAll();
	






}


