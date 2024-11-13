package com.sixtythreeebays.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sixtythreeebays.domain.Return;
import com.sixtythreeebays.dto.ReturnDTO;
import com.sixtythreeebays.dto.ReturnSearchDTO;
import com.sixtythreeebays.dto.ReturnPageDTO;
import com.sixtythreeebays.dto.ReturnConvertCriteriaDTO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ReturnService extends GenericService<Return, Integer> {

	List<Return> findAll();

	ResultDTO addReturn(ReturnDTO returnDTO, RequestDTO requestDTO);

	ResultDTO updateReturn(ReturnDTO returnDTO, RequestDTO requestDTO);

    Page<Return> getAllReturns(Pageable pageable);

    Page<Return> getAllReturns(Specification<Return> spec, Pageable pageable);

	ResponseEntity<ReturnPageDTO> getReturns(ReturnSearchDTO returnSearchDTO);
	
	List<ReturnDTO> convertReturnsToReturnDTOs(List<Return> returns, ReturnConvertCriteriaDTO convertCriteria);

	ReturnDTO getReturnDTOById(Integer returnId);







}





