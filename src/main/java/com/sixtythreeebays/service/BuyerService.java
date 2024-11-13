package com.sixtythreeebays.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sixtythreeebays.domain.Buyer;
import com.sixtythreeebays.dto.BuyerDTO;
import com.sixtythreeebays.dto.BuyerSearchDTO;
import com.sixtythreeebays.dto.BuyerPageDTO;
import com.sixtythreeebays.dto.BuyerConvertCriteriaDTO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BuyerService extends GenericService<Buyer, Integer> {

	List<Buyer> findAll();

	ResultDTO addBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO);

	ResultDTO updateBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO);

    Page<Buyer> getAllBuyers(Pageable pageable);

    Page<Buyer> getAllBuyers(Specification<Buyer> spec, Pageable pageable);

	ResponseEntity<BuyerPageDTO> getBuyers(BuyerSearchDTO buyerSearchDTO);
	
	List<BuyerDTO> convertBuyersToBuyerDTOs(List<Buyer> buyers, BuyerConvertCriteriaDTO convertCriteria);

	BuyerDTO getBuyerDTOById(Integer buyerId);







}





