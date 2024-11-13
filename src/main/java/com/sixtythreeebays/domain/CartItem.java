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
@Table(name="cart_items")
@Getter @Setter @NoArgsConstructor
public class CartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="cart_item_id")
	private Integer cartItemId;
    
  	@Column(name="quantity")
	private int quantity;
    
	




}
