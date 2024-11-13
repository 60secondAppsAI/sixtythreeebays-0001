package com.sixtythreeebays.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sixtythreeebays.domain.Feedback;
import com.sixtythreeebays.dto.FeedbackDTO;
import com.sixtythreeebays.dto.FeedbackSearchDTO;
import com.sixtythreeebays.dto.FeedbackPageDTO;
import com.sixtythreeebays.dto.FeedbackConvertCriteriaDTO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FeedbackService extends GenericService<Feedback, Integer> {

	List<Feedback> findAll();

	ResultDTO addFeedback(FeedbackDTO feedbackDTO, RequestDTO requestDTO);

	ResultDTO updateFeedback(FeedbackDTO feedbackDTO, RequestDTO requestDTO);

    Page<Feedback> getAllFeedbacks(Pageable pageable);

    Page<Feedback> getAllFeedbacks(Specification<Feedback> spec, Pageable pageable);

	ResponseEntity<FeedbackPageDTO> getFeedbacks(FeedbackSearchDTO feedbackSearchDTO);
	
	List<FeedbackDTO> convertFeedbacksToFeedbackDTOs(List<Feedback> feedbacks, FeedbackConvertCriteriaDTO convertCriteria);

	FeedbackDTO getFeedbackDTOById(Integer feedbackId);







}





