package com.bank.account.transfer.model;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CardTO {

	private int cardId;

	private int customerId;

	private String cardNumber;

	private String cardType;

	private int totalLimit;

	private int amountUsed;

	private int availableAmount;

	private Date createDt;

}
