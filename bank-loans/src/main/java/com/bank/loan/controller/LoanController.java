package com.bank.loan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loan.model.CustomerTO;
import com.bank.loan.model.Loan;
import com.bank.loan.repository.LoanRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LoanController {

	private LoanRepository loanRepository;
	
	@PostMapping("/myLoans")
	public List<Loan> getLoans(@RequestBody CustomerTO customer){		
		return loanRepository.findAllByCustomerId(customer.getId());
	}
}
