package com.sixtythreeebays.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="shippings")
@Getter @Setter @NoArgsConstructor
public class Shipping {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="shipping_id")
	private Integer shippingId;
    
  	@Column(name="shipping_address")
	private String shippingAddress;
    
  	@Column(name="shipping_method")
	private String shippingMethod;
    
  	@Column(name="tracking_number")
	private String trackingNumber;
    
	




}
