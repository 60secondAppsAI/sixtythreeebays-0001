package com.sixtythreeebays.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ProductDTO {

	private Integer productId;

	private String productName;

	private String description;

	private double price;

	private int stockQuantity;






}
