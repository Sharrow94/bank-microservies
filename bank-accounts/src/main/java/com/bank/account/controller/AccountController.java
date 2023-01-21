package com.bank.account.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.configuration.AccountsServiceConfig;
import com.bank.account.model.Account;
import com.bank.account.model.Properties;
import com.bank.account.repository.AccountRepository;
import com.bank.account.transfer.CustomerDetails;
import com.bank.account.transfer.feign.CardFeignClient;
import com.bank.account.transfer.feign.LoanFeignClient;
import com.bank.account.transfer.model.CardTO;
import com.bank.account.transfer.model.CustomerTO;
import com.bank.account.transfer.model.LoanTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AccountController {
	
	private static final Logger LOG=LoggerFactory.getLogger(AccountController.class);
	
	private AccountRepository accountRepository;

	private AccountsServiceConfig accountConfig;
	
	private LoanFeignClient loanFeignClient;

	private CardFeignClient cardFeignClient;
	
	@PostMapping("/myAccount")
	public Account getAccountDetails(@RequestBody CustomerTO customer) {
		return accountRepository.findByCustomerId(customer.getId());
	}
	
	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountConfig.getMsg(), accountConfig.getBuildVersion(),
				accountConfig.getMailDetails(), accountConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
	
	@PostMapping("/myCustomerDetails")	
	@CircuitBreaker(name = "detailsForCustomerSupportApp",fallbackMethod="myCustomerDetailsFallBack")
	@Retry(name = "retryForCustomerDetails", fallbackMethod = "myCustomerDetailsFallBack")
	public CustomerDetails myCustomerDetails(@RequestHeader("bank-correlation-id") String correlationid,@RequestBody CustomerTO customer) {
		LOG.info("myCustomerDetails() method started");
		Account account = accountRepository.findByCustomerId(customer.getId());
		List<LoanTO> loans = loanFeignClient.getLoanDetails(correlationid,customer);
		List<CardTO> cards = cardFeignClient.getCardDetails(correlationid,customer);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccount(account);
		customerDetails.setLoans(loans);
		customerDetails.setCards(cards);
		LOG.info("myCustomerDetails() method ended");
		return customerDetails;
	}
	
	private CustomerDetails myCustomerDetailsFallBack(@RequestHeader("bank-correlation-id") String correlationid,CustomerTO customer, Throwable t) {
		Account accounts = accountRepository.findByCustomerId(customer.getId());
		List<LoanTO> loans = loanFeignClient.getLoanDetails(correlationid,customer);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccount(accounts);
		customerDetails.setLoans(loans);
		return customerDetails;

	}
	
	@GetMapping("/sayHello")
	@RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
	public String sayHello() {
		return "Hello, Welcome to BankApp";
	}

	private String sayHelloFallback(Throwable t) {
		return "Hi, Welcome to BankApp";
	}

}
