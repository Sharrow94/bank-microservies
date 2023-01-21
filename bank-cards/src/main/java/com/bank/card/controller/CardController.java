package com.bank.card.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bank.card.config.CardServiceConfig;
import com.bank.card.model.Card;
import com.bank.card.model.CustomerTO;
import com.bank.card.model.Properties;
import com.bank.card.repository.CardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CardController {
	
	private static final Logger LOG=LoggerFactory.getLogger(CardController.class);

	private CardRepository cardsRepository;
	
	private CardServiceConfig cardsConfig;

	@PostMapping("/myCards")
	public List<Card> getCardDetails(@RequestHeader("bank-correlation-id") String correlationid,@RequestBody CustomerTO customer) {
		LOG.info("getCardDetails() method invoked");
		return cardsRepository.findByCustomerId(customer.getId());
	}
	
	@GetMapping("/cards/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
				cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
}
