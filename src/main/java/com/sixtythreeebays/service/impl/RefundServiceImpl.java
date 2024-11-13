package com.sixtythreeebays.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.sixtythreeebays.dao.GenericDAO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.service.impl.GenericServiceImpl;
import com.sixtythreeebays.dao.RefundDAO;
import com.sixtythreeebays.domain.Refund;
import com.sixtythreeebays.dto.RefundDTO;
import com.sixtythreeebays.dto.RefundSearchDTO;
import com.sixtythreeebays.dto.RefundPageDTO;
import com.sixtythreeebays.dto.RefundConvertCriteriaDTO;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import com.sixtythreeebays.service.RefundService;
import com.sixtythreeebays.util.ControllerUtils;





@Service
public class RefundServiceImpl extends GenericServiceImpl<Refund, Integer> implements RefundService {

    private final static Logger logger = LoggerFactory.getLogger(RefundServiceImpl.class);

	@Autowired
	RefundDAO refundDao;

	


	@Override
	public GenericDAO<Refund, Integer> getDAO() {
		return (GenericDAO<Refund, Integer>) refundDao;
	}
	
	public List<Refund> findAll () {
		List<Refund> refunds = refundDao.findAll();
		
		return refunds;	
		
	}

	public ResultDTO addRefund(RefundDTO refundDTO, RequestDTO requestDTO) {

		Refund refund = new Refund();

		refund.setRefundId(refundDTO.getRefundId());


		refund.setRefundAmount(refundDTO.getRefundAmount());


		refund.setRefundDate(refundDTO.getRefundDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		refund = refundDao.save(refund);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Refund> getAllRefunds(Pageable pageable) {
		return refundDao.findAll(pageable);
	}

	public Page<Refund> getAllRefunds(Specification<Refund> spec, Pageable pageable) {
		return refundDao.findAll(spec, pageable);
	}

	public ResponseEntity<RefundPageDTO> getRefunds(RefundSearchDTO refundSearchDTO) {
	
			Integer refundId = refundSearchDTO.getRefundId(); 
    			String sortBy = refundSearchDTO.getSortBy();
			String sortOrder = refundSearchDTO.getSortOrder();
			String searchQuery = refundSearchDTO.getSearchQuery();
			Integer page = refundSearchDTO.getPage();
			Integer size = refundSearchDTO.getSize();

	        Specification<Refund> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, refundId, "refundId"); 
			
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Refund> refunds = this.getAllRefunds(spec, pageable);
		
		//System.out.println(String.valueOf(refunds.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(refunds.getTotalPages()));
		
		List<Refund> refundsList = refunds.getContent();
		
		RefundConvertCriteriaDTO convertCriteria = new RefundConvertCriteriaDTO();
		List<RefundDTO> refundDTOs = this.convertRefundsToRefundDTOs(refundsList,convertCriteria);
		
		RefundPageDTO refundPageDTO = new RefundPageDTO();
		refundPageDTO.setRefunds(refundDTOs);
		refundPageDTO.setTotalElements(refunds.getTotalElements());
		return ResponseEntity.ok(refundPageDTO);
	}

	public List<RefundDTO> convertRefundsToRefundDTOs(List<Refund> refunds, RefundConvertCriteriaDTO convertCriteria) {
		
		List<RefundDTO> refundDTOs = new ArrayList<RefundDTO>();
		
		for (Refund refund : refunds) {
			refundDTOs.add(convertRefundToRefundDTO(refund,convertCriteria));
		}
		
		return refundDTOs;

	}
	
	public RefundDTO convertRefundToRefundDTO(Refund refund, RefundConvertCriteriaDTO convertCriteria) {
		
		RefundDTO refundDTO = new RefundDTO();
		
		refundDTO.setRefundId(refund.getRefundId());

	
		refundDTO.setRefundAmount(refund.getRefundAmount());

	
		refundDTO.setRefundDate(refund.getRefundDate());

	

		
		return refundDTO;
	}

	public ResultDTO updateRefund(RefundDTO refundDTO, RequestDTO requestDTO) {
		
		Refund refund = refundDao.getById(refundDTO.getRefundId());

		refund.setRefundId(ControllerUtils.setValue(refund.getRefundId(), refundDTO.getRefundId()));

		refund.setRefundAmount(ControllerUtils.setValue(refund.getRefundAmount(), refundDTO.getRefundAmount()));

		refund.setRefundDate(ControllerUtils.setValue(refund.getRefundDate(), refundDTO.getRefundDate()));



        refund = refundDao.save(refund);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RefundDTO getRefundDTOById(Integer refundId) {
	
		Refund refund = refundDao.getById(refundId);
			
		
		RefundConvertCriteriaDTO convertCriteria = new RefundConvertCriteriaDTO();
		return(this.convertRefundToRefundDTO(refund,convertCriteria));
	}







}
