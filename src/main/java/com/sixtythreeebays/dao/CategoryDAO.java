package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Category;





public interface CategoryDAO extends GenericDAO<Category, Integer> {
  
	List<Category> findAll();
	






}


