package com.bank.card.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class CustomerTO implements Serializable{

	private static final long serialVersionUID = 5879568363456313484L;

	private Long id;
	
	private String name;
	
	private String email;
	
	private String mobileNumber;
	
	private LocalDate createdAt;
}
