package com.capgemini.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders") 
public class Order implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@NotNull
	private String customerName;
	
	@NotNull
	private String foodItem;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private String status;

}
