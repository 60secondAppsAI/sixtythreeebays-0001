package com.sixtythreeebays.dao;

import java.util.List;

import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.domain.Auction;





public interface AuctionDAO extends GenericDAO<Auction, Integer> {
  
	List<Auction> findAll();
	






}


