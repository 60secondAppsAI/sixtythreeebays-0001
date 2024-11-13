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
import com.sixtythreeebays.dao.BuyerDAO;
import com.sixtythreeebays.domain.Buyer;
import com.sixtythreeebays.dto.BuyerDTO;
import com.sixtythreeebays.dto.BuyerSearchDTO;
import com.sixtythreeebays.dto.BuyerPageDTO;
import com.sixtythreeebays.dto.BuyerConvertCriteriaDTO;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import com.sixtythreeebays.service.BuyerService;
import com.sixtythreeebays.util.ControllerUtils;





@Service
public class BuyerServiceImpl extends GenericServiceImpl<Buyer, Integer> implements BuyerService {

    private final static Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);

	@Autowired
	BuyerDAO buyerDao;

	


	@Override
	public GenericDAO<Buyer, Integer> getDAO() {
		return (GenericDAO<Buyer, Integer>) buyerDao;
	}
	
	public List<Buyer> findAll () {
		List<Buyer> buyers = buyerDao.findAll();
		
		return buyers;	
		
	}

	public ResultDTO addBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO) {

		Buyer buyer = new Buyer();

		buyer.setBuyerId(buyerDTO.getBuyerId());


		buyer.setPurchaseHistory(buyerDTO.getPurchaseHistory());


		buyer.setWishlist(buyerDTO.getWishlist());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		buyer = buyerDao.save(buyer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Buyer> getAllBuyers(Pageable pageable) {
		return buyerDao.findAll(pageable);
	}

	public Page<Buyer> getAllBuyers(Specification<Buyer> spec, Pageable pageable) {
		return buyerDao.findAll(spec, pageable);
	}

	public ResponseEntity<BuyerPageDTO> getBuyers(BuyerSearchDTO buyerSearchDTO) {
	
			Integer buyerId = buyerSearchDTO.getBuyerId(); 
 			String purchaseHistory = buyerSearchDTO.getPurchaseHistory(); 
 			String wishlist = buyerSearchDTO.getWishlist(); 
 			String sortBy = buyerSearchDTO.getSortBy();
			String sortOrder = buyerSearchDTO.getSortOrder();
			String searchQuery = buyerSearchDTO.getSearchQuery();
			Integer page = buyerSearchDTO.getPage();
			Integer size = buyerSearchDTO.getSize();

	        Specification<Buyer> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, buyerId, "buyerId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, purchaseHistory, "purchaseHistory"); 
			
			spec = ControllerUtils.andIfNecessary(spec, wishlist, "wishlist"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("purchaseHistory")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("wishlist")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Buyer> buyers = this.getAllBuyers(spec, pageable);
		
		//System.out.println(String.valueOf(buyers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(buyers.getTotalPages()));
		
		List<Buyer> buyersList = buyers.getContent();
		
		BuyerConvertCriteriaDTO convertCriteria = new BuyerConvertCriteriaDTO();
		List<BuyerDTO> buyerDTOs = this.convertBuyersToBuyerDTOs(buyersList,convertCriteria);
		
		BuyerPageDTO buyerPageDTO = new BuyerPageDTO();
		buyerPageDTO.setBuyers(buyerDTOs);
		buyerPageDTO.setTotalElements(buyers.getTotalElements());
		return ResponseEntity.ok(buyerPageDTO);
	}

	public List<BuyerDTO> convertBuyersToBuyerDTOs(List<Buyer> buyers, BuyerConvertCriteriaDTO convertCriteria) {
		
		List<BuyerDTO> buyerDTOs = new ArrayList<BuyerDTO>();
		
		for (Buyer buyer : buyers) {
			buyerDTOs.add(convertBuyerToBuyerDTO(buyer,convertCriteria));
		}
		
		return buyerDTOs;

	}
	
	public BuyerDTO convertBuyerToBuyerDTO(Buyer buyer, BuyerConvertCriteriaDTO convertCriteria) {
		
		BuyerDTO buyerDTO = new BuyerDTO();
		
		buyerDTO.setBuyerId(buyer.getBuyerId());

	
		buyerDTO.setPurchaseHistory(buyer.getPurchaseHistory());

	
		buyerDTO.setWishlist(buyer.getWishlist());

	

		
		return buyerDTO;
	}

	public ResultDTO updateBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO) {
		
		Buyer buyer = buyerDao.getById(buyerDTO.getBuyerId());

		buyer.setBuyerId(ControllerUtils.setValue(buyer.getBuyerId(), buyerDTO.getBuyerId()));

		buyer.setPurchaseHistory(ControllerUtils.setValue(buyer.getPurchaseHistory(), buyerDTO.getPurchaseHistory()));

		buyer.setWishlist(ControllerUtils.setValue(buyer.getWishlist(), buyerDTO.getWishlist()));



        buyer = buyerDao.save(buyer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BuyerDTO getBuyerDTOById(Integer buyerId) {
	
		Buyer buyer = buyerDao.getById(buyerId);
			
		
		BuyerConvertCriteriaDTO convertCriteria = new BuyerConvertCriteriaDTO();
		return(this.convertBuyerToBuyerDTO(buyer,convertCriteria));
	}







}
