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
import com.sixtythreeebays.dao.ProductDAO;
import com.sixtythreeebays.domain.Product;
import com.sixtythreeebays.dto.ProductDTO;
import com.sixtythreeebays.dto.ProductSearchDTO;
import com.sixtythreeebays.dto.ProductPageDTO;
import com.sixtythreeebays.dto.ProductConvertCriteriaDTO;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import com.sixtythreeebays.service.ProductService;
import com.sixtythreeebays.util.ControllerUtils;





@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Integer> implements ProductService {

    private final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDAO productDao;

	


	@Override
	public GenericDAO<Product, Integer> getDAO() {
		return (GenericDAO<Product, Integer>) productDao;
	}
	
	public List<Product> findAll () {
		List<Product> products = productDao.findAll();
		
		return products;	
		
	}

	public ResultDTO addProduct(ProductDTO productDTO, RequestDTO requestDTO) {

		Product product = new Product();

		product.setProductId(productDTO.getProductId());


		product.setProductName(productDTO.getProductName());


		product.setDescription(productDTO.getDescription());


		product.setPrice(productDTO.getPrice());


		product.setStockQuantity(productDTO.getStockQuantity());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		product = productDao.save(product);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Product> getAllProducts(Pageable pageable) {
		return productDao.findAll(pageable);
	}

	public Page<Product> getAllProducts(Specification<Product> spec, Pageable pageable) {
		return productDao.findAll(spec, pageable);
	}

	public ResponseEntity<ProductPageDTO> getProducts(ProductSearchDTO productSearchDTO) {
	
			Integer productId = productSearchDTO.getProductId(); 
 			String productName = productSearchDTO.getProductName(); 
 			String description = productSearchDTO.getDescription(); 
   			String sortBy = productSearchDTO.getSortBy();
			String sortOrder = productSearchDTO.getSortOrder();
			String searchQuery = productSearchDTO.getSearchQuery();
			Integer page = productSearchDTO.getPage();
			Integer size = productSearchDTO.getSize();

	        Specification<Product> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, productId, "productId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, productName, "productName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("productName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Product> products = this.getAllProducts(spec, pageable);
		
		//System.out.println(String.valueOf(products.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(products.getTotalPages()));
		
		List<Product> productsList = products.getContent();
		
		ProductConvertCriteriaDTO convertCriteria = new ProductConvertCriteriaDTO();
		List<ProductDTO> productDTOs = this.convertProductsToProductDTOs(productsList,convertCriteria);
		
		ProductPageDTO productPageDTO = new ProductPageDTO();
		productPageDTO.setProducts(productDTOs);
		productPageDTO.setTotalElements(products.getTotalElements());
		return ResponseEntity.ok(productPageDTO);
	}

	public List<ProductDTO> convertProductsToProductDTOs(List<Product> products, ProductConvertCriteriaDTO convertCriteria) {
		
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		
		for (Product product : products) {
			productDTOs.add(convertProductToProductDTO(product,convertCriteria));
		}
		
		return productDTOs;

	}
	
	public ProductDTO convertProductToProductDTO(Product product, ProductConvertCriteriaDTO convertCriteria) {
		
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setProductId(product.getProductId());

	
		productDTO.setProductName(product.getProductName());

	
		productDTO.setDescription(product.getDescription());

	
		productDTO.setPrice(product.getPrice());

	
		productDTO.setStockQuantity(product.getStockQuantity());

	

		
		return productDTO;
	}

	public ResultDTO updateProduct(ProductDTO productDTO, RequestDTO requestDTO) {
		
		Product product = productDao.getById(productDTO.getProductId());

		product.setProductId(ControllerUtils.setValue(product.getProductId(), productDTO.getProductId()));

		product.setProductName(ControllerUtils.setValue(product.getProductName(), productDTO.getProductName()));

		product.setDescription(ControllerUtils.setValue(product.getDescription(), productDTO.getDescription()));

		product.setPrice(ControllerUtils.setValue(product.getPrice(), productDTO.getPrice()));

		product.setStockQuantity(ControllerUtils.setValue(product.getStockQuantity(), productDTO.getStockQuantity()));



        product = productDao.save(product);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ProductDTO getProductDTOById(Integer productId) {
	
		Product product = productDao.getById(productId);
			
		
		ProductConvertCriteriaDTO convertCriteria = new ProductConvertCriteriaDTO();
		return(this.convertProductToProductDTO(product,convertCriteria));
	}







}
