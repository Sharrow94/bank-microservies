package com.bank.account.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Account {
		
	@Id
	private Long accountNumber;

	private Long customerId;
	
	private String accountType;
	
	private String branchAddress;
	
	private LocalDate createdAt;
}
