package com.sixtythreeebays.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sixtythreeebays.domain.Transaction;
import com.sixtythreeebays.dto.TransactionDTO;
import com.sixtythreeebays.dto.TransactionSearchDTO;
import com.sixtythreeebays.dto.TransactionPageDTO;
import com.sixtythreeebays.dto.TransactionConvertCriteriaDTO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TransactionService extends GenericService<Transaction, Integer> {

	List<Transaction> findAll();

	ResultDTO addTransaction(TransactionDTO transactionDTO, RequestDTO requestDTO);

	ResultDTO updateTransaction(TransactionDTO transactionDTO, RequestDTO requestDTO);

    Page<Transaction> getAllTransactions(Pageable pageable);

    Page<Transaction> getAllTransactions(Specification<Transaction> spec, Pageable pageable);

	ResponseEntity<TransactionPageDTO> getTransactions(TransactionSearchDTO transactionSearchDTO);
	
	List<TransactionDTO> convertTransactionsToTransactionDTOs(List<Transaction> transactions, TransactionConvertCriteriaDTO convertCriteria);

	TransactionDTO getTransactionDTOById(Integer transactionId);







}





