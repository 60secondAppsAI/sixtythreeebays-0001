package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


