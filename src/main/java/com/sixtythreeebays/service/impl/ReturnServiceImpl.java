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
import com.sixtythreeebays.dao.ReturnDAO;
import com.sixtythreeebays.domain.Return;
import com.sixtythreeebays.dto.ReturnDTO;
import com.sixtythreeebays.dto.ReturnSearchDTO;
import com.sixtythreeebays.dto.ReturnPageDTO;
import com.sixtythreeebays.dto.ReturnConvertCriteriaDTO;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import com.sixtythreeebays.service.ReturnService;
import com.sixtythreeebays.util.ControllerUtils;





@Service
public class ReturnServiceImpl extends GenericServiceImpl<Return, Integer> implements ReturnService {

    private final static Logger logger = LoggerFactory.getLogger(ReturnServiceImpl.class);

	@Autowired
	ReturnDAO returnDao;

	


	@Override
	public GenericDAO<Return, Integer> getDAO() {
		return (GenericDAO<Return, Integer>) returnDao;
	}
	
	public List<Return> findAll () {
		List<Return> returns = returnDao.findAll();
		
		return returns;	
		
	}

	public ResultDTO addReturn(ReturnDTO returnDTO, RequestDTO requestDTO) {

		Return return = new Return();

		return.setReturnId(returnDTO.getReturnId());


		return.setReason(returnDTO.getReason());


		return.setReturnDate(returnDTO.getReturnDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		return = returnDao.save(return);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Return> getAllReturns(Pageable pageable) {
		return returnDao.findAll(pageable);
	}

	public Page<Return> getAllReturns(Specification<Return> spec, Pageable pageable) {
		return returnDao.findAll(spec, pageable);
	}

	public ResponseEntity<ReturnPageDTO> getReturns(ReturnSearchDTO returnSearchDTO) {
	
			Integer returnId = returnSearchDTO.getReturnId(); 
 			String reason = returnSearchDTO.getReason(); 
   			String sortBy = returnSearchDTO.getSortBy();
			String sortOrder = returnSearchDTO.getSortOrder();
			String searchQuery = returnSearchDTO.getSearchQuery();
			Integer page = returnSearchDTO.getPage();
			Integer size = returnSearchDTO.getSize();

	        Specification<Return> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, returnId, "returnId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, reason, "reason"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("reason")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Return> returns = this.getAllReturns(spec, pageable);
		
		//System.out.println(String.valueOf(returns.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(returns.getTotalPages()));
		
		List<Return> returnsList = returns.getContent();
		
		ReturnConvertCriteriaDTO convertCriteria = new ReturnConvertCriteriaDTO();
		List<ReturnDTO> returnDTOs = this.convertReturnsToReturnDTOs(returnsList,convertCriteria);
		
		ReturnPageDTO returnPageDTO = new ReturnPageDTO();
		returnPageDTO.setReturns(returnDTOs);
		returnPageDTO.setTotalElements(returns.getTotalElements());
		return ResponseEntity.ok(returnPageDTO);
	}

	public List<ReturnDTO> convertReturnsToReturnDTOs(List<Return> returns, ReturnConvertCriteriaDTO convertCriteria) {
		
		List<ReturnDTO> returnDTOs = new ArrayList<ReturnDTO>();
		
		for (Return return : returns) {
			returnDTOs.add(convertReturnToReturnDTO(return,convertCriteria));
		}
		
		return returnDTOs;

	}
	
	public ReturnDTO convertReturnToReturnDTO(Return return, ReturnConvertCriteriaDTO convertCriteria) {
		
		ReturnDTO returnDTO = new ReturnDTO();
		
		returnDTO.setReturnId(return.getReturnId());

	
		returnDTO.setReason(return.getReason());

	
		returnDTO.setReturnDate(return.getReturnDate());

	

		
		return returnDTO;
	}

	public ResultDTO updateReturn(ReturnDTO returnDTO, RequestDTO requestDTO) {
		
		Return return = returnDao.getById(returnDTO.getReturnId());

		return.setReturnId(ControllerUtils.setValue(return.getReturnId(), returnDTO.getReturnId()));

		return.setReason(ControllerUtils.setValue(return.getReason(), returnDTO.getReason()));

		return.setReturnDate(ControllerUtils.setValue(return.getReturnDate(), returnDTO.getReturnDate()));



        return = returnDao.save(return);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ReturnDTO getReturnDTOById(Integer returnId) {
	
		Return return = returnDao.getById(returnId);
			
		
		ReturnConvertCriteriaDTO convertCriteria = new ReturnConvertCriteriaDTO();
		return(this.convertReturnToReturnDTO(return,convertCriteria));
	}







}
