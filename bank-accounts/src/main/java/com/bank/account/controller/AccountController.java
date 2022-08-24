package com.bank.account.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.model.Account;
import com.bank.account.model.Customer;
import com.bank.account.repository.AccountRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AccountController {

	private AccountRepository accountRepository;
	
	@PostMapping("/myAccount")
	public  Account getAccountDetails(@RequestBody Customer customer) {
		return Optional.ofNullable(accountRepository.findByCustomerId(customer.getId())).orElse(null);
	}
}
