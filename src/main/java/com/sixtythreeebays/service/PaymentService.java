package com.sixtythreeebays.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sixtythreeebays.domain.Payment;
import com.sixtythreeebays.dto.PaymentDTO;
import com.sixtythreeebays.dto.PaymentSearchDTO;
import com.sixtythreeebays.dto.PaymentPageDTO;
import com.sixtythreeebays.dto.PaymentConvertCriteriaDTO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PaymentService extends GenericService<Payment, Integer> {

	List<Payment> findAll();

	ResultDTO addPayment(PaymentDTO paymentDTO, RequestDTO requestDTO);

	ResultDTO updatePayment(PaymentDTO paymentDTO, RequestDTO requestDTO);

    Page<Payment> getAllPayments(Pageable pageable);

    Page<Payment> getAllPayments(Specification<Payment> spec, Pageable pageable);

	ResponseEntity<PaymentPageDTO> getPayments(PaymentSearchDTO paymentSearchDTO);
	
	List<PaymentDTO> convertPaymentsToPaymentDTOs(List<Payment> payments, PaymentConvertCriteriaDTO convertCriteria);

	PaymentDTO getPaymentDTOById(Integer paymentId);







}





