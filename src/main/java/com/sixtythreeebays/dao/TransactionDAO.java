package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Transaction;





public interface TransactionDAO extends GenericDAO<Transaction, Integer> {
  
	List<Transaction> findAll();
	






}


