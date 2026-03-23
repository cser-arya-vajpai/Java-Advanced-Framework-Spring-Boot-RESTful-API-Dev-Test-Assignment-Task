package com.capgemini.entity;

import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

import jakarta.persistence.GeneratedValue;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Student implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private long phone;
	

}
