package com.bank.loan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loan.config.LoanServiceConfig;
import com.bank.loan.model.CustomerTO;
import com.bank.loan.model.Loan;
import com.bank.loan.model.Properties;
import com.bank.loan.repository.LoanRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LoanController {
	
	private static final Logger LOG=LoggerFactory.getLogger(LoanController.class);

	private LoanRepository loansRepository;
	
	private LoanServiceConfig loansConfig;

	@PostMapping("/myLoans")
	public List<Loan> getLoansDetails(@RequestHeader("bank-correlation-id") String correlationid,@RequestBody CustomerTO customer) {
		LOG.info("getLoansDetails() method invoked");
		return loansRepository.findByCustomerIdOrderByStartAtDesc(customer.getId());
	}
	
	@GetMapping("/loans/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(),
				loansConfig.getMailDetails(), loansConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
}
