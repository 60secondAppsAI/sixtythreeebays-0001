package com.sixtythreeebays.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuyerPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<BuyerDTO> buyers;
}





