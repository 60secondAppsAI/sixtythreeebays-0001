package com.sixtythreeebays.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sixtythreeebays.domain.Refund;
import com.sixtythreeebays.dto.RefundDTO;
import com.sixtythreeebays.dto.RefundSearchDTO;
import com.sixtythreeebays.dto.RefundPageDTO;
import com.sixtythreeebays.dto.RefundConvertCriteriaDTO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RefundService extends GenericService<Refund, Integer> {

	List<Refund> findAll();

	ResultDTO addRefund(RefundDTO refundDTO, RequestDTO requestDTO);

	ResultDTO updateRefund(RefundDTO refundDTO, RequestDTO requestDTO);

    Page<Refund> getAllRefunds(Pageable pageable);

    Page<Refund> getAllRefunds(Specification<Refund> spec, Pageable pageable);

	ResponseEntity<RefundPageDTO> getRefunds(RefundSearchDTO refundSearchDTO);
	
	List<RefundDTO> convertRefundsToRefundDTOs(List<Refund> refunds, RefundConvertCriteriaDTO convertCriteria);

	RefundDTO getRefundDTOById(Integer refundId);







}





