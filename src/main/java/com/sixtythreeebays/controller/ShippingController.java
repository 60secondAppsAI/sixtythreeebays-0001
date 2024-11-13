package com.sixtythreeebays.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.sixtythreeebays.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.sixtythreeebays.domain.Shipping;
import com.sixtythreeebays.dto.ShippingDTO;
import com.sixtythreeebays.dto.ShippingSearchDTO;
import com.sixtythreeebays.dto.ShippingPageDTO;
import com.sixtythreeebays.service.ShippingService;
import com.sixtythreeebays.dto.common.RequestDTO;
import com.sixtythreeebays.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/shipping")
@RestController
public class ShippingController {

	private final static Logger logger = LoggerFactory.getLogger(ShippingController.class);

	@Autowired
	ShippingService shippingService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Shipping> getAll() {

		List<Shipping> shippings = shippingService.findAll();
		
		return shippings;	
	}

	@GetMapping(value = "/{shippingId}")
	@ResponseBody
	public ShippingDTO getShipping(@PathVariable Integer shippingId) {
		
		return (shippingService.getShippingDTOById(shippingId));
	}

 	@RequestMapping(value = "/addShipping", method = RequestMethod.POST)
	public ResponseEntity<?> addShipping(@RequestBody ShippingDTO shippingDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shippingService.addShipping(shippingDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/shippings")
	public ResponseEntity<ShippingPageDTO> getShippings(ShippingSearchDTO shippingSearchDTO) {
 
		return shippingService.getShippings(shippingSearchDTO);
	}	

	@RequestMapping(value = "/updateShipping", method = RequestMethod.POST)
	public ResponseEntity<?> updateShipping(@RequestBody ShippingDTO shippingDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shippingService.updateShipping(shippingDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
