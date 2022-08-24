package com.bank.card.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bank.card.model.Card;
import com.bank.card.model.CustomerTO;
import com.bank.card.repository.CardRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CardController {

	private CardRepository cardRepository;

	@PostMapping("/myCards")
	public List<Card> getCardDetails(String correlationid,@RequestBody CustomerTO customer) {
		return cardRepository.findByCustomerId(customer.getId());
	}
}
