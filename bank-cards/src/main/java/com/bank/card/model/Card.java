package com.bank.card.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;

	private Long customerId;

	private String cardNumber;

	private String cardType;

	private BigDecimal totalLimit;

	private BigDecimal amountUsed;

	private BigDecimal availableAmount;

	private LocalDate createDt;

}
