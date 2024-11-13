package com.sixtythreeebays.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.sixtythreeebays.domain.Cart;
import com.sixtythreeebays.dto.CartDTO;
import com.sixtythreeebays.dto.CartSearchDTO;
import com.sixtythreeebays.dto.CartPageDTO;
import com.sixtythreeebays.dto.CartConvertCriteriaDTO;
import com.sixtythreeebays.service.GenericService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CartService extends GenericService<Cart, Integer> {

	List<Cart> findAll();

	ResultDTO addCart(CartDTO cartDTO, RequestDTO requestDTO);

	ResultDTO updateCart(CartDTO cartDTO, RequestDTO requestDTO);

    Page<Cart> getAllCarts(Pageable pageable);

    Page<Cart> getAllCarts(Specification<Cart> spec, Pageable pageable);

	ResponseEntity<CartPageDTO> getCarts(CartSearchDTO cartSearchDTO);
	
	List<CartDTO> convertCartsToCartDTOs(List<Cart> carts, CartConvertCriteriaDTO convertCriteria);

	CartDTO getCartDTOById(Integer cartId);







}





