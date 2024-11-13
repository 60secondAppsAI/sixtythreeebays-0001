package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


