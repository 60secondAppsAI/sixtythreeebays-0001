package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


