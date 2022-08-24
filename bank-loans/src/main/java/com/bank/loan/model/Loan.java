package com.bank.loan.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanNumber;
	
	private Long customerId;
	
	private LocalDate startAt;
	
	private String loanType;
	
	private BigDecimal totalLoan;
	
	private BigDecimal amountPaid;
	
	private BigDecimal outstandingAmount;
	
	private String createAt;
}
