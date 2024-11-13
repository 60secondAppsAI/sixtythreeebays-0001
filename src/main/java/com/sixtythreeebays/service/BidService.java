package com.sixtythreeebays.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sixtythreeebays.domain.Bid;
import com.sixtythreeebays.dto.BidDTO;
import com.sixtythreeebays.dto.BidSearchDTO;
import com.sixtythreeebays.dto.BidPageDTO;
import com.sixtythreeebays.dto.BidConvertCriteriaDTO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BidService extends GenericService<Bid, Integer> {

	List<Bid> findAll();

	ResultDTO addBid(BidDTO bidDTO, RequestDTO requestDTO);

	ResultDTO updateBid(BidDTO bidDTO, RequestDTO requestDTO);

    Page<Bid> getAllBids(Pageable pageable);

    Page<Bid> getAllBids(Specification<Bid> spec, Pageable pageable);

	ResponseEntity<BidPageDTO> getBids(BidSearchDTO bidSearchDTO);
	
	List<BidDTO> convertBidsToBidDTOs(List<Bid> bids, BidConvertCriteriaDTO convertCriteria);

	BidDTO getBidDTOById(Integer bidId);







}





