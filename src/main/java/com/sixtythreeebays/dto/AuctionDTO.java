package com.sixtythreeebays.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AuctionDTO {

	private Integer auctionId;

	private double startingPrice;

	private double currentPrice;

	private Date endDate;






}
